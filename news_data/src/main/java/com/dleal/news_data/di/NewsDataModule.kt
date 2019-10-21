package com.dleal.news_data.di

import androidx.room.Room
import com.dleal.core.BuildConfig
import com.dleal.news_data.local.database.NewsDatabase
import com.dleal.news_data.local.NewsLocalDataSource
import com.dleal.news_data.remote.NewsRemoteDataSource
import com.dleal.news_data.repository.NewsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Daniel Leal on 2019-10-20.
 */
val newsDataModule = module {

    single {
        val databaseBuilder =
            Room.databaseBuilder(androidContext(), NewsDatabase::class.java, "newsDatabase")
        if (BuildConfig.DEBUG) {
            databaseBuilder.fallbackToDestructiveMigration()
        }

        databaseBuilder.build()
    }

    single {
        NewsRemoteDataSource()
    }

    single {
        NewsLocalDataSource(get())
    }

    single {
        NewsRepository(get(), get())
    }
}