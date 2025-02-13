import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

//plugins {
//    // this is necessary to avoid the plugins to be loaded multiple times
//    // in each subproject's classloader
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.androidLibrary) apply false
//    alias(libs.plugins.composeMultiplatform) apply false
//    alias(libs.plugins.composeCompiler) apply false
//    alias(libs.plugins.kotlinMultiplatform) apply false
//}

buildscript {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(libs.gradlePlugin.android)
        classpath(libs.gradlePlugin.jetbrainsCompose)
        classpath(libs.gradlePlugin.kotlin)
        classpath(libs.gradlePlugin.kotlinComposeCompiler)
        classpath(libs.gradlePlugin.mavenPublish)
    }
}

allprojects {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

allprojects {
    kotlinDependenciesConfig()
    jvmTargetConfig()
    publishConfig()
}

fun Project.kotlinDependenciesConfig() {
    dependencies {
        modules {
            module("org.jetbrains.kotlin:kotlin-stdlib-jdk7") {
                replacedBy("org.jetbrains.kotlin:kotlin-stdlib")
            }
            module("org.jetbrains.kotlin:kotlin-stdlib-jdk8") {
                replacedBy("org.jetbrains.kotlin:kotlin-stdlib")
            }
        }
    }
}

fun Project.jvmTargetConfig() {
    // Target JVM 8.
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
        options.compilerArgs = options.compilerArgs + "-Xlint:-options"
    }
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions.jvmTarget = JvmTarget.JVM_1_8
    }
}

fun Project.publishConfig() {
    if (
//        && hasProperty("mavenCentralUsername")    // configured in the ~/.gradle/gradle.properties file
//        && hasProperty("mavenCentralPassword")    // configured in the ~/.gradle/gradle.properties file
        hasProperty("versionName")    // configured in the rootProject/gradle.properties file
        && hasProperty("GROUP")    // configured in the rootProject/gradle.properties file
        && hasProperty("POM_ARTIFACT_ID")    // configured in the project/gradle.properties file
    ) {
        apply { plugin("com.vanniktech.maven.publish") }

        configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
            version = property("versionName").toString()
            if (hasProperty("signing.keyId")    // configured in the ~/.gradle/gradle.properties file
                && hasProperty("signing.password")    // configured in the ~/.gradle/gradle.properties file
                && hasProperty("signing.secretKeyRingFile")    // configured in the ~/.gradle/gradle.properties file
            ) {
                // Local gradle global properties
                signAllPublications()
            } else if (
                hasProperty("ORG_GRADLE_PROJECT_signingInMemoryKey")    // configured in the github workflow env
                && hasProperty("ORG_GRADLE_PROJECT_signingInMemoryKeyPassword")    // configured in the github workflow env
            ) {
                // github workflow
                signAllPublications()
            }
        }
    }
}