#!/usr/bin/env python3
"""
Figma UI Validation Script
Figma spec JSON과 실제 Kotlin 코드를 비교해 HTML 리포트를 생성합니다.

Usage:
  python3 scripts/validate_ui.py
  python3 scripts/validate_ui.py --specs scripts/figma_specs --output build/reports/figma-validation/report.html
"""

import argparse
import base64
import json
import re
import sys
import urllib.request
from dataclasses import dataclass, field
from datetime import datetime
from pathlib import Path
from typing import Optional


# ──────────────────────────────────────────────
# Data models
# ──────────────────────────────────────────────

@dataclass
class CheckResult:
    check_id: str
    description: str
    status: str          # "pass" | "fail" | "skip"
    expected: str
    actual: Optional[str]
    figma_value: str = ""
    section: str = ""
    note: str = ""


@dataclass
class ComponentResult:
    node_id: str
    component: str
    figma_url: str
    target_file: str
    extracted_at: str
    screenshot_b64: Optional[str] = None
    checks: list[CheckResult] = field(default_factory=list)

    @property
    def pass_count(self):
        return sum(1 for c in self.checks if c.status == "pass")

    @property
    def fail_count(self):
        return sum(1 for c in self.checks if c.status == "fail")

    @property
    def skip_count(self):
        return sum(1 for c in self.checks if c.status == "skip")

    @property
    def overall(self):
        if self.fail_count > 0:
            return "fail"
        if self.skip_count == len(self.checks):
            return "skip"
        return "pass"

    @property
    def sections(self):
        seen = []
        for c in self.checks:
            s = c.section or "기타"
            if s not in seen:
                seen.append(s)
        return seen


# ──────────────────────────────────────────────
# Screenshot fetcher
# ──────────────────────────────────────────────

def fetch_screenshot_b64(node_id: str) -> Optional[str]:
    """Figma MCP 로컬 서버에서 스크린샷을 가져와 base64로 반환."""
    try:
        node_dashed = node_id.replace(":", "-")
        url = f"http://localhost:3845/screenshot/{node_dashed}"
        data = urllib.request.urlopen(url, timeout=5).read()
        return base64.b64encode(data).decode()
    except Exception:
        pass

    # 대안: assets 경로 시도 (MCP 서버 구조에 따라 다를 수 있음)
    try:
        url = f"http://localhost:3845/node-screenshot?nodeId={node_id}"
        data = urllib.request.urlopen(url, timeout=5).read()
        return base64.b64encode(data).decode()
    except Exception:
        return None


def load_screenshot_b64(spec: dict, spec_dir: Path) -> Optional[str]:
    """spec에 정의된 방식으로 스크린샷 로드."""
    # 1. spec JSON에 base64 직접 내장된 경우 (가장 우선)
    if spec.get("screenshot_b64"):
        return spec["screenshot_b64"]

    # 2. 로컬 PNG 파일 참조
    screenshot_path = spec.get("screenshot_path")
    if screenshot_path:
        full = spec_dir / screenshot_path
        if full.exists():
            return base64.b64encode(full.read_bytes()).decode()

    # 3. MCP 서버에서 fetch (로컬 실행 시만 동작)
    node_id = spec.get("screenshot_nodeId") or spec.get("nodeId")
    if node_id:
        return fetch_screenshot_b64(node_id)

    return None


# ──────────────────────────────────────────────
# Validator
# ──────────────────────────────────────────────

def load_spec(spec_path: Path) -> dict:
    with open(spec_path, encoding="utf-8") as f:
        return json.load(f)


def read_target_file(project_root: Path, target_file: str) -> Optional[str]:
    full_path = project_root / target_file
    if not full_path.exists():
        return None
    return full_path.read_text(encoding="utf-8")


def run_check(check: dict, source: str) -> CheckResult:
    check_id = check["id"]
    description = check["description"]
    check_type = check["type"]
    expected = str(check["expected"])
    figma_value = check.get("figma_value", expected)
    section = check.get("section", "")

    if check_type == "text_contains":
        found = expected in source
        return CheckResult(
            check_id=check_id,
            description=description,
            status="pass" if found else "fail",
            expected=expected,
            actual=expected if found else "(없음)",
            figma_value=figma_value,
            section=section,
        )

    elif check_type in ("dp_value", "token", "regex_match"):
        pattern = check.get("pattern")
        if not pattern:
            return CheckResult(check_id, description, "skip", expected, None,
                               figma_value, section, "pattern 없음")

        match = re.search(pattern, source)
        if not match:
            return CheckResult(
                check_id=check_id,
                description=description,
                status="fail",
                expected=expected,
                actual="(패턴 불일치)",
                figma_value=figma_value,
                section=section,
                note=f"regex: {pattern}",
            )

        actual = match.group(1)
        return CheckResult(
            check_id=check_id,
            description=description,
            status="pass" if actual == expected else "fail",
            expected=expected,
            actual=actual,
            figma_value=figma_value,
            section=section,
        )

    return CheckResult(check_id, description, "skip", expected, None,
                       figma_value, section, f"알 수 없는 type: {check_type}")


def validate_spec(spec: dict, project_root: Path, spec_path: Path) -> ComponentResult:
    result = ComponentResult(
        node_id=spec.get("nodeId", ""),
        component=spec.get("component", "Unknown"),
        figma_url=spec.get("figmaUrl", ""),
        target_file=spec.get("targetFile", ""),
        extracted_at=spec.get("extractedAt", ""),
        screenshot_b64=load_screenshot_b64(spec, spec_path.parent),
    )

    source = read_target_file(project_root, spec["targetFile"])
    if source is None:
        result.checks.append(CheckResult(
            check_id="__file__",
            description="파일 존재 여부",
            status="fail",
            expected=spec["targetFile"],
            actual="(파일 없음)",
        ))
        return result

    for check in spec.get("checks", []):
        result.checks.append(run_check(check, source))

    return result


# ──────────────────────────────────────────────
# HTML Report Generator
# ──────────────────────────────────────────────

def status_badge(status: str) -> str:
    icons = {"pass": "✅", "fail": "❌", "skip": "⚠️"}
    colors = {"pass": "#22c55e", "fail": "#ef4444", "skip": "#f59e0b"}
    labels = {"pass": "PASS", "fail": "FAIL", "skip": "SKIP"}
    return (
        f'<span style="background:{colors[status]};color:#fff;'
        f'padding:2px 8px;border-radius:4px;font-size:11px;font-weight:700;">'
        f'{icons.get(status,"")} {labels.get(status, status)}</span>'
    )


def render_screenshot_panel(result: ComponentResult) -> str:
    fail_sections = {c.section for c in result.checks if c.status == "fail" and c.section}

    if result.screenshot_b64:
        img_tag = f'<img src="data:image/png;base64,{result.screenshot_b64}" style="width:100%;border-radius:8px;display:block;" alt="Figma screenshot">'
    elif result.figma_url:
        img_tag = f'''
        <div style="display:flex;flex-direction:column;align-items:center;justify-content:center;height:300px;background:#1f2127;border-radius:8px;gap:12px;">
          <span style="font-size:32px;">🎨</span>
          <span style="color:#7b8696;font-size:13px;">스크린샷 없음</span>
          <a href="{result.figma_url}" target="_blank" style="color:#8d83e8;font-size:12px;">Figma에서 보기 ↗</a>
        </div>'''
    else:
        return ""

    fail_list = ""
    if fail_sections:
        items = "".join(f'<li style="margin-bottom:4px;color:#ef4444;">❌ {s}</li>' for s in sorted(fail_sections))
        fail_list = f'<div style="margin-top:12px;"><p style="color:#7b8696;font-size:12px;margin-bottom:6px;">불일치 섹션</p><ul style="padding-left:16px;font-size:13px;">{items}</ul></div>'

    return f'''
    <div style="width:220px;flex-shrink:0;">
      <p style="color:#7b8696;font-size:12px;margin-bottom:8px;font-weight:600;">FIGMA 디자인</p>
      {img_tag}
      {fail_list}
    </div>'''


def render_component(r: ComponentResult) -> str:
    # 섹션별로 체크 그룹화
    sections: dict[str, list[CheckResult]] = {}
    for c in r.checks:
        key = c.section or "기타"
        sections.setdefault(key, []).append(c)

    sections_html = ""
    for section_name, checks in sections.items():
        section_fail = any(c.status == "fail" for c in checks)
        section_color = "#ef444420" if section_fail else "#22c55e15"
        rows = ""
        for c in checks:
            actual_display = c.actual or "-"
            # 불일치 시 실제값에 빨간 강조
            if c.status == "fail":
                actual_display = f'<span style="color:#ef4444;font-weight:600;">{actual_display}</span>'
            rows += f"""
            <tr>
              <td style="padding:8px 12px;border-bottom:1px solid #2a2f38;">{c.description}</td>
              <td style="padding:8px 12px;border-bottom:1px solid #2a2f38;font-family:monospace;color:#d9ceff;font-size:12px;">{c.figma_value or c.expected}</td>
              <td style="padding:8px 12px;border-bottom:1px solid #2a2f38;font-family:monospace;font-size:12px;">{actual_display}</td>
              <td style="padding:8px 12px;border-bottom:1px solid #2a2f38;text-align:center;">{status_badge(c.status)}</td>
              <td style="padding:8px 12px;border-bottom:1px solid #2a2f38;color:#7b8696;font-size:11px;">{c.note}</td>
            </tr>"""

        sections_html += f"""
        <div style="margin-bottom:4px;">
          <div style="background:{section_color};padding:6px 12px;border-left:3px solid {'#ef4444' if section_fail else '#22c55e'};">
            <span style="font-size:12px;font-weight:600;color:#a5b2c5;">{section_name}</span>
            {'<span style="margin-left:8px;font-size:11px;color:#ef4444;">불일치 있음</span>' if section_fail else ''}
          </div>
          <table style="width:100%;border-collapse:collapse;font-size:13px;color:#d7e1ee;">
            <thead>
              <tr style="background:#1a1d22;">
                <th style="padding:6px 12px;text-align:left;color:#7b8696;font-weight:600;font-size:11px;border-bottom:1px solid #2a2f38;">항목</th>
                <th style="padding:6px 12px;text-align:left;color:#7b8696;font-weight:600;font-size:11px;border-bottom:1px solid #2a2f38;">Figma</th>
                <th style="padding:6px 12px;text-align:left;color:#7b8696;font-weight:600;font-size:11px;border-bottom:1px solid #2a2f38;">코드</th>
                <th style="padding:6px 12px;text-align:center;color:#7b8696;font-weight:600;font-size:11px;border-bottom:1px solid #2a2f38;">결과</th>
                <th style="padding:6px 12px;text-align:left;color:#7b8696;font-weight:600;font-size:11px;border-bottom:1px solid #2a2f38;">비고</th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
          </table>
        </div>"""

    overall_bg = "#1a2a1a" if r.overall == "pass" else "#2a1a1a"
    figma_link = f'<a href="{r.figma_url}" style="color:#8d83e8;font-size:12px;" target="_blank">Figma ↗</a>' if r.figma_url else ""
    screenshot_panel = render_screenshot_panel(r)

    return f"""
    <div style="background:#17191f;border:1px solid #2a2f38;border-radius:12px;margin-bottom:32px;overflow:hidden;">
      <div style="background:{overall_bg};padding:14px 20px;display:flex;justify-content:space-between;align-items:center;border-bottom:1px solid #2a2f38;">
        <div style="display:flex;align-items:center;gap:12px;">
          <span style="font-size:16px;font-weight:700;color:#f4f4f5;">{r.component}</span>
          <span style="font-size:11px;color:#7b8696;">node: {r.node_id}</span>
          {figma_link}
        </div>
        <div style="display:flex;gap:12px;align-items:center;">
          <span style="font-size:11px;color:#7b8696;">기준일: {r.extracted_at}</span>
          {status_badge(r.overall)}
        </div>
      </div>
      <div style="padding:8px 20px;background:#1a1d22;border-bottom:1px solid #2a2f38;">
        <code style="font-size:11px;color:#a5b2c5;">{r.target_file}</code>
      </div>
      <div style="display:flex;gap:0;align-items:flex-start;">
        {f'<div style="padding:16px 0 16px 16px;">{screenshot_panel}</div>' if screenshot_panel else ''}
        <div style="flex:1;min-width:0;">
          {sections_html}
        </div>
      </div>
      <div style="padding:10px 20px;background:#1a1d22;border-top:1px solid #2a2f38;display:flex;gap:16px;">
        <span style="font-size:12px;color:#22c55e;">✅ {r.pass_count} 통과</span>
        <span style="font-size:12px;color:#ef4444;">❌ {r.fail_count} 실패</span>
        <span style="font-size:12px;color:#f59e0b;">⚠️ {r.skip_count} 스킵</span>
      </div>
    </div>"""


def generate_html(results: list[ComponentResult], generated_at: str) -> str:
    total_pass = sum(r.pass_count for r in results)
    total_fail = sum(r.fail_count for r in results)
    total_skip = sum(r.skip_count for r in results)
    total = total_pass + total_fail + total_skip
    pass_rate = round(total_pass / total * 100) if total > 0 else 0
    overall_color = "#22c55e" if total_fail == 0 else "#ef4444"

    components_html = "".join(render_component(r) for r in results)

    return f"""<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Figma UI Validation Report</title>
  <style>
    * {{ box-sizing: border-box; margin: 0; padding: 0; }}
    body {{ font-family: -apple-system, 'Wanted Sans', sans-serif; background: #0f1114; color: #d7e1ee; padding: 32px; }}
  </style>
</head>
<body>
  <div style="max-width:1100px;margin:0 auto;">

    <div style="margin-bottom:28px;">
      <h1 style="font-size:22px;font-weight:700;color:#f4f4f5;margin-bottom:4px;">Figma UI Validation Report</h1>
      <p style="color:#7b8696;font-size:12px;">생성 시각: {generated_at}</p>
    </div>

    <div style="display:grid;grid-template-columns:repeat(4,1fr);gap:12px;margin-bottom:28px;">
      <div style="background:#17191f;border:1px solid #2a2f38;border-radius:10px;padding:16px;">
        <div style="font-size:12px;color:#7b8696;margin-bottom:6px;">검증 컴포넌트</div>
        <div style="font-size:26px;font-weight:700;color:#f4f4f5;">{len(results)}</div>
      </div>
      <div style="background:#17191f;border:1px solid #2a2f38;border-radius:10px;padding:16px;">
        <div style="font-size:12px;color:#7b8696;margin-bottom:6px;">통과</div>
        <div style="font-size:26px;font-weight:700;color:#22c55e;">{total_pass}</div>
      </div>
      <div style="background:#17191f;border:1px solid #2a2f38;border-radius:10px;padding:16px;">
        <div style="font-size:12px;color:#7b8696;margin-bottom:6px;">실패</div>
        <div style="font-size:26px;font-weight:700;color:#ef4444;">{total_fail}</div>
      </div>
      <div style="background:#17191f;border:1px solid #2a2f38;border-radius:10px;padding:16px;">
        <div style="font-size:12px;color:#7b8696;margin-bottom:6px;">통과율</div>
        <div style="font-size:26px;font-weight:700;color:{overall_color};">{pass_rate}%</div>
      </div>
    </div>

    {components_html}

    <div style="text-align:center;margin-top:24px;color:#3d424b;font-size:11px;">
      Generated by Figma UI Validator · DHC Android
    </div>
  </div>
</body>
</html>"""


# ──────────────────────────────────────────────
# Main
# ──────────────────────────────────────────────

def main():
    parser = argparse.ArgumentParser(description="Figma UI Validation")
    parser.add_argument("--specs", default="scripts/figma_specs", help="spec JSON 디렉토리")
    parser.add_argument("--output", default="build/reports/figma-validation/report.html", help="HTML 리포트 출력 경로")
    parser.add_argument("--root", default=".", help="프로젝트 루트 경로")
    args = parser.parse_args()

    project_root = Path(args.root).resolve()
    specs_dir = project_root / args.specs
    output_path = project_root / args.output

    if not specs_dir.exists():
        print(f"❌ specs 디렉토리를 찾을 수 없습니다: {specs_dir}")
        sys.exit(1)

    spec_files = sorted(specs_dir.glob("*.json"))
    if not spec_files:
        print(f"⚠️  {specs_dir} 에 JSON 파일이 없습니다.")
        sys.exit(0)

    print(f"📋 {len(spec_files)}개 spec 파일 발견")

    results = []
    for spec_file in spec_files:
        spec = load_spec(spec_file)
        result = validate_spec(spec, project_root, spec_file)
        results.append(result)
        screenshot_icon = "🖼️" if result.screenshot_b64 else "  "
        icon = "✅" if result.overall == "pass" else "❌"
        print(f"  {icon} {screenshot_icon} {result.component}: {result.pass_count} 통과 / {result.fail_count} 실패")

    output_path.parent.mkdir(parents=True, exist_ok=True)
    html = generate_html(results, datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
    output_path.write_text(html, encoding="utf-8")
    print(f"\n📄 리포트 생성: {output_path}")

    total_fail = sum(r.fail_count for r in results)
    if total_fail > 0:
        print(f"❌ {total_fail}개 항목 실패 — CI를 종료합니다.")
        sys.exit(1)

    print("✅ 모든 항목 통과")


if __name__ == "__main__":
    main()
