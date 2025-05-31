import com.google.protobuf.gradle.id

plugins {
    id("dhc.module")
    id("dhc.hilt")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.dhc.data"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.bundles.network)

    implementation(libs.bundles.proto.datastore)

    implementation(libs.bundles.flipper)

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
