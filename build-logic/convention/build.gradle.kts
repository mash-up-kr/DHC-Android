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
        register("configFlavor") {
            id = "dhc.config-flavor"
            implementationClass = "ConfigFlavorPlugin"
        }

        register("hilt") {
            id = "dhc.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}
