plugins {
    id("dhc.module")
    id("dhc.hilt")
}

android {
    namespace = "com.dhc.data"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.bundles.network)

    debugImplementation(libs.bundles.flipper)

    testImplementation(libs.junit)
}
