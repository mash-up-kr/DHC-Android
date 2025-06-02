plugins {
    id("dhc.application")
    id("dhc.hilt")
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.gms)
}

android {
    namespace = "com.dhc.dhcandroid"
}

dependencies {
    detektPlugins(libs.detekt.formatting)

    implementation(libs.bundles.flipper)
    implementation(project(":core:presentation"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging.ktx)
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
}
