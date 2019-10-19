const val kotlinVersion = "1.3.50"

object AndroidSdk {
    const val min = 21
    const val compile = 28
    const val target = compile
}

object Core {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val buildToolsVersion = "29.0.0"
}

object AndroidX {
    private object Versions {
        const val jetpack = "1.0.2"
        const val ktx = "1.0.2"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val core = "androidx.core:core-ktx:${Versions.ktx}"
}

object Rx {
    private object Versions {
        const val rxJava = "2.1.10"
        const val rxAndroid = "2.1.0"
        const val rxKotlin = "2.2.0"
    }

    const val java = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
}

object Koin {
    private const val version = "2.0.1"
    const val core = "org.koin:koin-core:$version"
    const val android = "org.koin:koin-android:$version"
    const val scope = "org.koin:koin-androidx-scope:$version"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:$version"
    const val testing = "org.koin:koin-test:$version"
}