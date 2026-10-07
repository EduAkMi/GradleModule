plugins {
    id("plugin.android-module")
}

android {
    namespace = "com.gradlemodule.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
}
