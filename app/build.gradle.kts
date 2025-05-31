plugins {
    id("dhc.application")
    id("dhc.hilt")
    alias(libs.plugins.detekt)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.dhc.dhcandroid"
}

dependencies {
    implementation(libs.androidx.profileinstaller)
    "baselineProfile"(project(":baselineprofile"))

    detektPlugins(libs.detekt.formatting)

    implementation(libs.bundles.flipper)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
}
