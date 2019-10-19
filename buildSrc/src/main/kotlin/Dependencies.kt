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