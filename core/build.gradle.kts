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
        getByName("debug"){
            buildConfigField("boolean", "DEBUG_ENABLED", "true")

            isMinifyEnabled = false
        }

        getByName("release") {
            buildConfigField("boolean", "DEBUG_ENABLED", "false")

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    api(Core.kotlin_stdlib)

    //AndroidX
    api(AndroidX.appCompat)
    api(AndroidX.core)

    api(AndroidX.navigationFragment)
    api(AndroidX.navigationUi)

    //Rx
    api(Rx.java)
    api(Rx.android)
    api(Rx.kotlin)

    //Koin
    api(Koin.android)
}
