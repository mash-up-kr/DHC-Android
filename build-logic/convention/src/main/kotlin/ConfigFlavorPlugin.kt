import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class ConfigFlavorPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        requireNotNull(target.extensions.findByType(CommonExtension::class)).let { extension ->
            target.configureFlavor(extension)
        }
    }
}

internal fun Project.configureFlavor(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    with(commonExtension) {
        productFlavors {
            Flavor.values().forEach { flavor ->
                if (flavorDimensions.contains(flavor.dimen).not()) {
                    flavorDimensions += flavor.dimen
                }

                create(flavor.flavor) {
                    this.dimension = flavor.dimen
                }
            }
        }
    }
}

private enum class Flavor(val flavor: String, val dimen: String) {
    DEV("dev", "server"),
    PROD("real", "server")
}