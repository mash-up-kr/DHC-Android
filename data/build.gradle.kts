plugins {
    id("dhc.module")
    id("dhc.hilt")
}

android {
    namespace = "com.dhc.data"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.bundles.network)

    debugImplementation(libs.flipper)
    debugImplementation(libs.flipper.soloader)
    debugImplementation(libs.flipper.network)

    testImplementation(libs.junit)
}
