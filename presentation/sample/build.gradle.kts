plugins {
    id("dhc.presentation")
    id("dhc.hilt")
}

android {
    namespace = "com.dhc.sample"
}

dependencies {
    testImplementation(libs.junit)
}
