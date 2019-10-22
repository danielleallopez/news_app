package com.dleal.core.di

import com.dleal.core.BuildConfig
import com.dleal.core.utils.RxTransformer
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModule = module {
    single(named(DEBUG_ENABLED)) { BuildConfig.DEBUG_ENABLED }

    //RxTransformer
    single { RxTransformer() }
}

const val DEBUG_ENABLED = "DEBUG_ENABLED"