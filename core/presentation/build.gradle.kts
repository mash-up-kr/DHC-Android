plugins {
    id("dhc.module")
    id("dhc.compose")
}

android {
    namespace = "com.dhc.presentation"
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    testImplementation(libs.junit)
}
