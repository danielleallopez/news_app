package com.dleal.mynews

import android.app.Application
import com.dleal.core.di.coreModule
import com.dleal.data.di.dataModule
import com.dleal.news_data.di.newsDataModule
import com.dleal.news_view.news_list.di.newsViewModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Daniel Leal on 2019-10-21.
 */
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()

        Stetho.initializeWithDefaults(this)
    }

    private fun initKoin() {
        startKoin {

            // use the Android context given there
            androidContext(this@NewsApplication)

            // module list
            modules(
                appModules
            )
        }
    }
}

val appModules = listOf(
    coreModule,
    dataModule,
    newsDataModule,
    newsViewModule
)