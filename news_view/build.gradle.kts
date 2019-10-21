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

        buildConfigField("int", "DATABASE_VERSION", "1")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))

    kapt(Room.compiler)
    implementation(Room.rxjava)
    implementation(Room.kotlin)
}
