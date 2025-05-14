package com.dhc.buildlogic.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    with(commonExtension) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        buildFeatures {
            compose = true
        }

        dependencies {
            implementation(platform(libs.library("androidx.compose.bom")))
            implementation(libs.bundle("androidx.compose"))
            debugImplementation(libs.library("androidx.ui.tooling"))
        }
    }
}