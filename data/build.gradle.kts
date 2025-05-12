plugins {
    id("dhc.module")
}

android {
    namespace = "com.dhc.data"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}
