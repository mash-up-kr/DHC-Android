plugins {
    id("dhc.presentation")
    id("dhc.hilt")
}

android {
    namespace = "com.dhc.intro"
}

dependencies {
    implementation(libs.bundles.exoplayer)
    testImplementation(libs.junit)
}
