import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    id("dhc.application")
    id("dhc.hilt")
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.gms)
}

android {
    namespace = "com.dhc.dhcandroid"
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    lintChecks(libs.compose.lint)

    implementation(libs.bundles.flipper)
    implementation(project(":core:presentation"))

    implementation(libs.androidx.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging.ktx)
}

detekt {
    toolVersion = libs.versions.detekt.get()
    buildUponDefaultConfig = true
    autoCorrect = true
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}