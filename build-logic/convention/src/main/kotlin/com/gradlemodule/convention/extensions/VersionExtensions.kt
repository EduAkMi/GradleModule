package com.gradlemodule.convention.extensions

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.artifacts.VersionCatalog

internal fun LibraryExtension.configureVersions(versionCatalog: VersionCatalog) {
    compileSdk = versionCatalog.getVersionInt("build-sdk-compile")

    defaultConfig {
        minSdk = versionCatalog.getVersionInt("build-sdk-min")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
}
