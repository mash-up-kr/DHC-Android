plugins {
    id("dhc.module")
    id("dhc.compose")
}

android {
    namespace = "com.dhc.presentation"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.bundles.exoplayer)
    implementation(libs.lottie.compose)
    testImplementation(libs.junit)
}
