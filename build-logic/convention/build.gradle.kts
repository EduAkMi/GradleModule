plugins {
    `kotlin-dsl`
}

group = "com.gradlemodule.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidModule") {
            id = "plugin.android-module"
            implementationClass = "AndroidModulePlugin"
        }
    }
}
