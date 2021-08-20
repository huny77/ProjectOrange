dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
        maven("https://jitpack.io")
    }
}

rootProject.name = "orange"

include(
    ":app",
    ":data",
    ":common",
    ":domain",
)
include(":compose")
