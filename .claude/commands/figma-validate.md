# Figma → Code 검증 에이전트

Figma 노드에서 디자인 데이터를 추출하고, 코드와 비교해 spec JSON 저장 + HTML 리포트 생성까지 한 번에 수행한다.

## 입력 파싱

`$ARGUMENTS`에서 다음을 파싱한다:
- Figma node-id 또는 URL (필수) — URL이면 `node-id=1-2` 부분을 `1:2` 형식으로 변환
- 코드 파일 경로 (선택) — 없으면 node 이름 기반으로 프로젝트에서 관련 파일을 Glob/Grep으로 탐색

## 실행 순서

### 1단계: Figma 데이터 수집 (동시 실행)

아래 세 도구를 **동시에** 호출한다:
- `mcp__figma-local__get_design_context` — 레이아웃, 사이즈, 텍스트 스타일, reference code 추출
- `mcp__figma-local__get_variable_defs` — 색상 토큰, 스페이싱 변수 추출
- `mcp__figma-local__get_screenshot` — 스크린샷 획득

### 2단계: 스크린샷 base64 저장

Figma MCP HTTP API를 직접 호출해 스크린샷 base64를 가져온다.
아래 Python 스크립트를 Bash 도구로 실행한다:

```bash
python3 - << 'PYEOF'
import urllib.request, json
from pathlib import Path

url = "http://127.0.0.1:3845/mcp"

init = json.dumps({"jsonrpc":"2.0","id":1,"method":"initialize","params":{
    "protocolVersion":"2024-11-05","capabilities":{},"clientInfo":{"name":"validator","version":"1.0"}
}}).encode()
req = urllib.request.Request(url, data=init,
    headers={"Content-Type":"application/json","Accept":"application/json, text/event-stream"})
with urllib.request.urlopen(req, timeout=10) as resp:
    session_id = resp.headers.get("mcp-session-id")

def mcp_call(method, params, req_id):
    body = json.dumps({"jsonrpc":"2.0","id":req_id,"method":method,"params":params}).encode()
    req = urllib.request.Request(url, data=body, headers={
        "Content-Type":"application/json",
        "Accept":"application/json, text/event-stream",
        "mcp-session-id": session_id
    })
    with urllib.request.urlopen(req, timeout=30) as resp:
        for line in resp.read().decode().split("\n"):
            if line.startswith("data: "):
                return json.loads(line[6:])

result = mcp_call("tools/call", {"name":"get_screenshot","arguments":{"nodeId":"{NODE_ID}"}}, 2)
for item in result["result"]["content"]:
    if item.get("type") == "image":
        print("SCREENSHOT_B64:" + item["data"])
        break
PYEOF
```

출력에서 `SCREENSHOT_B64:` 이후 문자열을 추출해 다음 단계에서 사용한다.

### 3단계: 코드 파일 특정

코드 파일이 지정되지 않은 경우:
1. `get_design_context`에서 컴포넌트 이름을 파악
2. `Glob("**/*.kt")`와 `Grep`으로 관련 Kotlin 파일 탐색
3. 가장 관련성 높은 파일 선택

코드 파일이 지정된 경우: 해당 파일과 `core/designsystem/Color.kt`, `core/designsystem/Typography.kt`도 함께 읽는다.

### 4단계: checks 배열 생성

추출된 Figma 값을 기반으로 아래 규칙으로 checks를 생성한다:

**텍스트 문구** (`type: text_contains`)
- Figma 텍스트 레이어의 content 추출
- `expected`: 해당 텍스트 문자열

**dp 수치** (`type: dp_value`)
- padding, cornerRadius, size, gap 등
- `expected`: 숫자값 (문자열)
- `pattern`: 해당 값을 찾는 Kotlin regex

일반적인 패턴 예시:
| 항목 | pattern |
|------|---------|
| horizontal padding | `padding\\(horizontal\\s*=\\s*(\\d+)\\.dp` |
| vertical padding | `padding\\(.*vertical\\s*=\\s*(\\d+)\\.dp` |
| cornerRadius | `RoundedCornerShape\\((\\d+)\\.dp\\)` |
| height spacer | `Modifier\\.height\\((\\d+)\\.dp\\)` |
| gap | `spacedBy\\((\\d+)\\.dp\\)` |

**토큰** (`type: token`)
- 색상 토큰, 타이포그래피 토큰
- `expected`: 토큰 이름 문자열
- `pattern`: 토큰을 찾는 Kotlin regex

일반적인 패턴 예시:
| 항목 | pattern |
|------|---------|
| 색상 토큰 | `color\\s*=\\s*colors\\.text\\.(\\w+)` |
| 타이포 토큰 | `style\\s*=\\s*DhcTypoTokens\\.(\\w+)` |

각 check에는 `section` 필드도 포함한다 (예: "전반적인 운세", "Typography" 등).

### 5단계: spec JSON 저장

파일명: `scripts/figma_specs/{nodeId}_{컴포넌트명}.json`
(nodeId의 `:` → `-` 변환, 컴포넌트명은 PascalCase)

기존 파일이 있으면 덮어쓴다. 아래 Bash 스크립트로 저장한다:

```bash
python3 - << 'PYEOF'
import json
from pathlib import Path

spec = {
    "nodeId": "{NODE_ID}",
    "component": "{COMPONENT_NAME}",
    "figmaUrl": "{FIGMA_URL}",
    "targetFile": "{TARGET_FILE}",
    "extractedAt": "{TODAY_DATE}",
    "screenshot_b64": "{SCREENSHOT_B64}",
    "checks": {CHECKS_JSON}
}

path = Path("scripts/figma_specs/{FILE_NAME}.json")
path.write_text(json.dumps(spec, ensure_ascii=False, indent=2))
print(f"✅ spec 저장: {path}")
PYEOF
```

### 6단계: validate_ui.py 실행 + HTML 리포트

```bash
python3 scripts/validate_ui.py && open build/reports/figma-validation/report.html
```

실패해도 (`exit 1`) HTML은 생성되므로 반드시 열어서 결과를 보여준다.

### 7단계: 결과 리포트 출력 (터미널)

```
## Figma 검증 결과: {컴포넌트명}
노드 ID: {node-id}
검증 파일: {파일 경로}

### ✅ 일치 ({N}개)
### ❌ 불일치 ({N}개)
| 항목 | Figma | 코드 | 수정 방향 |
|------|-------|------|----------|

### 수정 필요 코드 (불일치 항목이 있을 경우)

📄 HTML 리포트: build/reports/figma-validation/report.html
```

## 사용 예시

```
/figma-validate 8380:36134
/figma-validate 8380:36134 presentation/reward/src/main/java/com/dhc/reward/yearfortune/YearFortuneScreen.kt
/figma-validate https://figma.com/design/xxx/yyy?node-id=8380-36134
```
