plugins {
    id("dhc.presentation")
    id("dhc.hilt")
    id("dhc.compose")
}
android {
    namespace = "com.dhc.reward"
}

dependencies {
    testImplementation(libs.junit)
}

