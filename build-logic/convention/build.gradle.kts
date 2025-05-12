plugins {
    `kotlin-dsl`
}

group = "com.dhc.dhcandroid"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = "dhc.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        register("compose") {
            id = "dhc.compose"
            implementationClass = "ComposeConventionPlugin"
        }

        register("application") {
            id = "dhc.application"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("presentation") {
            id = "dhc.presentation"
            implementationClass = "PresentationConventionPlugin"
        }

        register("module") {
            id = "dhc.module"
            implementationClass = "ModuleConventionPlugin"
        }
    }
}
