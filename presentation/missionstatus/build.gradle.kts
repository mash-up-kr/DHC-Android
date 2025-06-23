plugins {
    id("dhc.presentation")
    id("dhc.hilt")
    id("dhc.compose")
}
android {
    namespace = "com.dhc.missionstatus"
}

dependencies {
    testImplementation(libs.junit)
}
