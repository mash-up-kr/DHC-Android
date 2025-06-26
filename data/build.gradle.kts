import com.google.protobuf.gradle.id

plugins {
    id("dhc.module")
    id("dhc.hilt")
    alias(libs.plugins.protobuf)
    alias(libs.plugins.kotlinx.serialization)
}

val localProps = rootProject.file("local.properties")
    .reader()
    .useLines { lines ->
        lines.find { it.startsWith("DHC_SERVER_BASE_URL=") }
            ?.substringAfter("=")
            ?.trim()
    } ?: throw GradleException("BASE_URL not found")

android {
    namespace = "com.dhc.data"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "DHC_SERVER_BASE_URL", "\"$localProps\"")
    }
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.bundles.network)

    implementation(libs.bundles.proto.datastore)

    debugImplementation(libs.bundles.flipper)

    testImplementation(libs.junit)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.0"
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("java") {
                    option("lite")
                }
            }
        }
    }
}
