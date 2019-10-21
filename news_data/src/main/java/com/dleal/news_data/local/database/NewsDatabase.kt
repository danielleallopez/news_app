package com.dleal.news_data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dleal.data.datasources.local.converters.AppConverters
import com.dleal.news_data.BuildConfig

/**
 * Created by Daniel Leal on 2019-10-19.
 */

@Database(
    entities = [
        ArticleData::class,
        VideoData::class
    ],
    version = BuildConfig.DATABASE_VERSION
)

@TypeConverters(AppConverters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}