package com.dhc.buildlogic.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureFlavor(
    commonExtension: CommonExtension<*, *, *, *, * , *>,
) {
    with(commonExtension) {
        productFlavors {
            Flavor.values().forEach { flavor ->
                if (flavorDimensions.contains(flavor.dimen).not()) {
                    flavorDimensions += flavor.dimen
                }

                create(flavor.flavor) {
                    this.dimension = flavor.dimen
                }
            }
        }
    }
}

private enum class Flavor(val flavor: String, val dimen: String) {
    PROD("real", "server"),
    DEV("dev", "server")
}
