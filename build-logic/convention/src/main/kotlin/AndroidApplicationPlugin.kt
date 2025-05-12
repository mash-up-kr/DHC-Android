import com.android.build.api.dsl.ApplicationExtension
import com.dhc.buildlogic.util.configureCompose
import com.dhc.buildlogic.util.configureFlavor
import com.dhc.buildlogic.util.configureKotlinAndroid
import com.dhc.buildlogic.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureCompose(this)
                configureFlavor(this)

                defaultConfig.applicationId = "com.dhc.dhcandroid"
            }

            extensions.configure<ApplicationExtension> {
                configureCompose(this)

                with(defaultConfig) {
                    targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
                    versionCode = libs.findVersion("versionCode").get().requiredVersion.toInt()
                    versionName = libs.findVersion("versionName").get().requiredVersion
                }
            }
        }
    }
}