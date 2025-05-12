package com.dhc.buildlogic.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    with(commonExtension) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        buildFeatures {
            compose = true
        }

        dependencies {
            val composeBom = platform(libs.findLibrary("androidx.compose.bom").get())
            implementation(composeBom)
            implementation(libs.findBundle("androidx.compose").get())
            debugImplementation(libs.findLibrary("androidx.ui.tooling").get())
            debugImplementation(libs.findLibrary("androidx.ui.test.manifest").get())
            androidTestImplementation(composeBom)
            androidTestImplementation(libs.findLibrary("androidx.ui.test.junit4").get())
        }
    }
}