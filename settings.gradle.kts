pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		maven("https://maven.fabricmc.net/")
	}
}

rootProject.name = "mod"
include("fabric")
