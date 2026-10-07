package com.gradlemodule.convention.extensions

import com.android.build.api.dsl.LibraryExtension
import com.gradlemodule.convention.model.BuildType

private const val PROGUARD_FILE_NAME = "proguard-rules.pro"

internal fun LibraryExtension.configureBuildTypes() {
    buildTypes {
        getByName(BuildType.RELEASE.type) {
            consumerProguardFiles(PROGUARD_FILE_NAME)
            enableUnitTestCoverage = false
        }
        getByName(BuildType.DEBUG.type) {
            consumerProguardFiles(PROGUARD_FILE_NAME)
            enableUnitTestCoverage = false
        }
    }
}
