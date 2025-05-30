plugins {
    id("dhc.application")
    id("dhc.hilt")
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.dhc.dhcandroid"
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    lintChecks(libs.compose.lint)

    implementation(libs.bundles.flipper)

    implementation(libs.androidx.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
}
