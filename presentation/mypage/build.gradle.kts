plugins {
    id("dhc.presentation")
    id("dhc.hilt")
    id("dhc.compose")
}
android {
    namespace = "com.dhc.mypage"
}

dependencies {
    implementation(libs.bundles.coil)
    testImplementation(libs.junit)
}
