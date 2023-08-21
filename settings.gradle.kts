rootProject.name = "NeonKeyboardNew"
include(":app", ":aosp")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://www.jitpack.io" ) }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://www.jitpack.io" ) }

    }
}

