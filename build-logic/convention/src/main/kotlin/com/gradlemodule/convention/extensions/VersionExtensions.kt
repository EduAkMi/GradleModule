package com.gradlemodule.convention.extensions

import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.gradlemodule.convention.model.AppVersionExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register

internal fun ApplicationExtension.configureApplicationVersions(versionCatalog: VersionCatalog) {
    compileSdk = versionCatalog.getVersionInt("build-sdk-compile")

    defaultConfig {
        minSdk = versionCatalog.getVersionInt("build-sdk-min")
        targetSdk = versionCatalog.getVersionInt("build-sdk-target")

        versionCode = AppVersionExtension.VERSION_CODE
        versionName = AppVersionExtension.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

// O AGP 9 removeu applicationVariants/BaseVariantOutputImpl: o APK com o nome
// app_<flavor>_<buildType>_<versionName>.apk sai numa copia depois do assemble
internal fun Project.configureApplicationApkName() {
    extensions.configure<ApplicationAndroidComponentsExtension> {
        onVariants { variant ->
            val variantName = variant.name.replaceFirstChar { it.uppercase() }
            val apkName = variant.outputs.first().versionName.map { versionName ->
                listOf("app", variant.flavorName.orEmpty(), variant.buildType.orEmpty(), versionName)
                    .filter { it.isNotEmpty() }
                    .joinToString("_", postfix = ".apk")
            }

            val renameApk = tasks.register<Copy>("rename${variantName}Apk") {
                from(variant.artifacts.get(SingleArtifact.APK)) { include("*.apk") }
                into(layout.buildDirectory.dir("outputs/apk-versioned/${variant.name}"))
                rename { apkName.get() }
            }

            tasks.matching { it.name == "assemble$variantName" }.configureEach { finalizedBy(renameApk) }
        }
    }
}

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
