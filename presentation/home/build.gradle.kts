plugins {
    id("dhc.presentation")
    id("dhc.hilt")
    id("dhc.compose")
}

android {
    namespace = "com.dhc.home"
}

dependencies {
    testImplementation(libs.junit)
}
