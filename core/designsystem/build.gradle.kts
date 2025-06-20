plugins {
    id("dhc.module")
    id("dhc.compose")
}

android {
    namespace = "com.dhc.designsystem"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.compose)
    testImplementation(libs.junit)
}
