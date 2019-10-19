import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("kapt")
}

android {
    buildToolsVersion(Core.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        compileSdkVersion(AndroidSdk.compile)
        targetSdkVersion(AndroidSdk.target)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        this as KotlinJvmOptions
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))

    api(Api.gson)
    implementation(Room.runtime)
    kapt(Room.compiler)
}
