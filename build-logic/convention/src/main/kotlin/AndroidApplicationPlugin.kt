import com.android.build.api.dsl.ApplicationExtension
import com.dhc.buildlogic.util.configureAndroidLibrary
import com.dhc.buildlogic.util.configureCompose
import com.dhc.buildlogic.util.configureFlavor
import com.dhc.buildlogic.util.configureKotlinAndroid
import com.dhc.buildlogic.util.implementation
import com.dhc.buildlogic.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig.applicationId = "com.dhc.dhcandroid"

                with(defaultConfig) {
                    targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
                    versionCode = libs.findVersion("versionCode").get().requiredVersion.toInt()
                    versionName = libs.findVersion("versionName").get().requiredVersion
                }

                configureKotlinAndroid(this)
                configureAndroidLibrary(this)
                configureCompose(this)
                configureFlavor(this)
            }

            dependencies {
                implementation(project(":presentation:intro"))
                implementation(project(":presentation:home"))
                implementation(project(":presentation:missionstatus"))
                implementation(project(":data"))
                implementation(project(":core:designsystem"))
                implementation(project(":core:presentation"))
                implementation(project(":core:common"))
            }
        }
    }
}
