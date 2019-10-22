package com.dleal.data.di

import com.google.gson.GsonBuilder
import org.koin.dsl.module

val dataModule = module {

    //Gson
    single {
        GsonBuilder().create()
    }
}