plugins {
    id("dhc.application")
    id("dhc.hilt")
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.dhc.dhcandroid"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    detektPlugins(libs.detekt.formatting)
}
