plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.coroutines.core)
    implementation(libs.bundles.exposed)
    implementation(libs.postgresql)
    implementation(libs.hikari)
    implementation(libs.koin.core)
    implementation(libs.bundles.logging)

    testImplementation(libs.bundles.testcontainers)
}
