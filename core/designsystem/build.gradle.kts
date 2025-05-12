plugins {
    id("dhc.module")
    id("dhc.compose")
}

android {
    namespace = "com.dhc.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}
