rootProject.name = "AutoReleaseSample"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

include(":composeApp")
include(":library-compose")
include(":library-core")
include(":library-view")
