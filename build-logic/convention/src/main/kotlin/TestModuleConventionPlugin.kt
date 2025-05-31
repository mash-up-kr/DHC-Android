import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestExtension
import com.dhc.buildlogic.util.configureFlavor
import com.dhc.buildlogic.util.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

// Android 테스트 전용 모듈에 사용
class TestModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
            }

            extensions.configure<TestExtension> {
                configureFlavor(this)
                configureKotlinAndroid(this, false)

                defaultConfig.consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}
