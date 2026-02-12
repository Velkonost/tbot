plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.coroutines.core)
    implementation(libs.telegram.bot)
    implementation(libs.koin.core)
    implementation(libs.bundles.logging)
}
