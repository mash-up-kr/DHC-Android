import com.android.build.api.dsl.CommonExtension
import com.dhc.buildlogic.util.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        requireNotNull(target.extensions.findByType(CommonExtension::class)).let { extension ->
            target.configureCompose(extension)
        }
    }
}