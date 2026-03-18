pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}

rootProject.name = "quarkus-jimmer-example"

include("quarkus-jimmer-example-model")
include("quarkus-jimmer-example-service")
