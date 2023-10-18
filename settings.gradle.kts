pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "dance_tracker"
include(":app")

include(":core")
include(":core:common")
include(":core:datastore")
include(":core:design_system")
include(":core:model")
include(":core:ui")

include("feature:onboarding")
