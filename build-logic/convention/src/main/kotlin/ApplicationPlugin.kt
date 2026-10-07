import com.android.build.api.dsl.ApplicationExtension
import com.gradlemodule.convention.extensions.configureApplicationApkName
import com.gradlemodule.convention.extensions.configureApplicationCompilerOptions
import com.gradlemodule.convention.extensions.configureApplicationVersions
import com.gradlemodule.convention.extensions.getVersionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                configureApplicationVersions(getVersionCatalog())
                configureApplicationCompilerOptions()
            }

            configureApplicationApkName()
        }
    }
}
