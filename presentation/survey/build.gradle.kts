plugins {
    id("dhc.presentation")
    id("dhc.hilt")
    id("dhc.compose")
}
android {
    namespace = "com.dhc.survey"
}

dependencies {
    testImplementation(libs.junit)
}
