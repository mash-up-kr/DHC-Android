plugins {
    id("dhc.application")
    id("dhc.hilt")
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.dhc.dhcandroid"
}

dependencies {
    implementation(project(":presentation:sample"))
    implementation(project(":data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:presentation"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    detektPlugins(libs.detekt.formatting)
}
