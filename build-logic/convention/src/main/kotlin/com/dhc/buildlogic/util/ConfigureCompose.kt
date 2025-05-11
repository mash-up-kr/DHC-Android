package com.dhc.buildlogic.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    with(commonExtension) {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        buildFeatures {
            compose = true
        }

        dependencies {
            val composeBom = platform(libs.findLibrary("androidx.compose.bom").get())
            add("implementation", composeBom)
            add("implementation", libs.findBundle("androidx.compose").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
            add("androidTestImplementation", composeBom)
            add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
        }
    }
}