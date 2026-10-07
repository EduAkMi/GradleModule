plugins {
    `kotlin-dsl`
}

group = "com.gradlemodule.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("application") {
            id = "plugin.application"
            implementationClass = "ApplicationPlugin"
        }
        register("androidModule") {
            id = "plugin.android-module"
            implementationClass = "AndroidModulePlugin"
        }
    }
}
