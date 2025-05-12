plugins {
    id("dhc.presentation")
    id("dhc.hilt")
}

android {
    namespace = "com.dhc.sample"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:presentation"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
}
