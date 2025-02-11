plugins {
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()

androidLibrary(nameSpace = "com.github.panpf.auto.release.compose")

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.libraryCore)
            api(compose.components.resources)
            api(compose.foundation)
            api(compose.material)
            api(compose.runtime)
            api(compose.ui)
            api(compose.uiUtil)
            api(libs.kotlinx.collections.immutable)
        }
    }
}

compose.resources {
    packageOfResClass = "com.github.panpf.auto.release.compose.resources"
}