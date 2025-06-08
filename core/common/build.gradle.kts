plugins {
    id("dhc.module")
    id("dhc.compose")
}

android {
    namespace = "com.dhc.common"
}

dependencies {
    testImplementation(libs.junit)
}
