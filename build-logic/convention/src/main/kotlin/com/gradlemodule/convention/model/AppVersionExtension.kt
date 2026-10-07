package com.gradlemodule.convention.model

object AppVersionExtension {
    private const val MAJOR: Int = 1
    private const val MINOR: Int = 0
    private const val RELEASE: Int = 0
    private const val HOTFIX: Int = 0
    const val VERSION_NAME = "$MAJOR.$MINOR.$RELEASE.$HOTFIX"
    const val VERSION_CODE = 1
}
