plugins {
    id("dhc.presentation")
    id("dhc.hilt")
    id("dhc.compose")
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.dhc.home"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

roborazzi {
    outputDir.set(rootProject.file("scripts/figma_specs/roborazzi"))
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.rule)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.androidx.ui.test.manifest)
}
