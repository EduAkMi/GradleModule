package com.gradlemodule.convention.extensions

import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Packaging
import org.gradle.api.JavaVersion

internal fun LibraryExtension.configureCompilerOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packaging {
        configurePackageResources()
    }
}

private fun Packaging.configurePackageResources() {
    resources {
        excludes += listOf(
            "META-INF/AL2.0", "META-INF/LGPL2.1", "META-INF/LICENSE.md", "META-INF/LICENSE-notice.md",
            "META-INF/licenses/ASM"
        )
        pickFirsts += listOf("win32-x86-64/attach_hotspot_windows.dll", "win32-x86/attach_hotspot_windows.dll")
    }
}
