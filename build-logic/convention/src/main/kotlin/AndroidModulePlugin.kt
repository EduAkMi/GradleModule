import com.android.build.api.dsl.LibraryExtension
import com.gradlemodule.convention.extensions.configureBuildTypes
import com.gradlemodule.convention.extensions.configureCompilerOptions
import com.gradlemodule.convention.extensions.configureVersions
import com.gradlemodule.convention.extensions.getVersionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // AGP 9 ja compila Kotlin sozinho: nao precisa aplicar org.jetbrains.kotlin.android
            pluginManager.apply("com.android.library")

            extensions.configure<LibraryExtension> {
                configureBuildTypes()
                configureVersions(getVersionCatalog())
                configureCompilerOptions()
            }
        }
    }
}
