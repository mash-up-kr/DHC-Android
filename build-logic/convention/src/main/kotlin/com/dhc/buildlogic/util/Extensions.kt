package com.dhc.buildlogic.util

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun DependencyHandlerScope.implementation(
    dependencyNotation: Any
) {
    dependencies.add("implementation", dependencyNotation)
}

internal fun DependencyHandlerScope.debugImplementation(
    dependencyNotation: Any
) {
    dependencies.add("debugImplementation", dependencyNotation)
}

internal fun DependencyHandlerScope.androidTestImplementation(
    dependencyNotation: Any
) {
    dependencies.add("androidTestImplementation", dependencyNotation)
}

internal fun DependencyHandlerScope.kapt(
    dependencyNotation: Any
) {
    dependencies.add("kapt", dependencyNotation)
}

internal fun VersionCatalog.bundle(name: String) = findBundle(name).get()

internal fun VersionCatalog.library(name: String) = findLibrary(name).get()