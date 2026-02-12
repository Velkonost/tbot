plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

application {
    mainClass.set("com.velkonost.tbot.MainKt")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    implementation(libs.coroutines.core)
    implementation(libs.koin.core)
    implementation(libs.bundles.logging)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.velkonost.tbot.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
