package com.dhc.buildlogic.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidLibrary(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    with(commonExtension) {
        dependencies {
            add("implementation", libs.findBundle("androidx").get())
        }
    }
}