plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

androidLibrary(nameSpace = "com.github.panpf.auto.release.view") {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(libs.androidx.appcompat)
    api(projects.libraryCore)
}