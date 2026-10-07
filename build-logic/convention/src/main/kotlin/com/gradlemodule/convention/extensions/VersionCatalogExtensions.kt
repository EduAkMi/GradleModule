package com.gradlemodule.convention.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.getVersionCatalog(): VersionCatalog {
    return extensions.getByType<VersionCatalogsExtension>().named("libs")
}

internal fun VersionCatalog.getVersionInt(libName: String): Int = getVersion(libName).toInt()

internal fun VersionCatalog.getVersion(libName: String): String = findVersion(libName).get().requiredVersion

internal fun VersionCatalog.getLib(libName: String) = findLibrary(libName).get()
