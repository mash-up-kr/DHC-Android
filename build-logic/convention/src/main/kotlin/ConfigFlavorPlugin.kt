import com.android.build.api.dsl.CommonExtension
import com.dhc.buildlogic.util.configureFlavor
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