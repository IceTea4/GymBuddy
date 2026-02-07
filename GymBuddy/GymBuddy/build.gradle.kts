// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("checkstyle")
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}

// Add this below plugins
checkstyle {
    toolVersion = "10.12.3" // latest stable Checkstyle version
    configFile = file("${rootDir}/google_checks.xml")
    isIgnoreFailures = false
}

tasks.withType<Checkstyle> {
    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}