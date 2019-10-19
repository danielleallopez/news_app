package com.dleal.data.datasources.local

import androidx.room.RoomDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Daniel Leal on 2019-10-19.
 */
abstract class BaseLocalDataSource : KoinComponent {
    protected val database: RoomDatabase by inject()
}