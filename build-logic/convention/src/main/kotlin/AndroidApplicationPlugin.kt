import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.dhc.buildlogic.util.configureCompose
import com.dhc.buildlogic.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            requireNotNull(target.extensions.findByType(CommonExtension::class)).let { extension ->
                configureCompose(extension)
            }

            extensions.configure<ApplicationExtension> {
                configureCompose(this)

                with(defaultConfig) {
                    targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
                    versionCode = libs.findVersion("versionCode").get().requiredVersion.toInt()
                    versionName = libs.findVersion("versionName").get().requiredVersion
                }
            }

            dependencies {
                "detektPlugins"(libs.findLibrary("detekt.formatting").get())
            }
        }
    }
}