plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    buildToolsVersion(Core.buildToolsVersion)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        compileSdkVersion(AndroidSdk.compile)
        targetSdkVersion(AndroidSdk.target)

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
    implementation(project(":news_data"))
    implementation(project(":domain"))

    implementation(AndroidX.constraintLayout)

    implementation(Koin.viewmodel)
}
