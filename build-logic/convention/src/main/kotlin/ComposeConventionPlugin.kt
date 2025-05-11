import com.android.build.api.dsl.CommonExtension
import com.dhc.buildlogic.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType

class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        requireNotNull(target.extensions.findByType(CommonExtension::class)).let { extension ->
            target.configureCompose(extension)
        }
    }
}

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    with(commonExtension) {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        buildFeatures {
            compose = true
        }

        dependencies {
            val composeBom = platform(libs.findLibrary("androidx.compose.bom").get())
            add("implementation", composeBom)
            add("implementation", libs.findBundle("androidx.compose").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
            add("androidTestImplementation", composeBom)
            add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
        }
    }
}