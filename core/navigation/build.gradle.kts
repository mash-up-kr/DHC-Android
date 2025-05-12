plugins {
    id("dhc.module")
}

android {
    namespace = "com.dhc.navigation"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}
