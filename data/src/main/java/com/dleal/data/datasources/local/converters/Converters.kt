package com.dleal.data.datasources.local.converters

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Daniel Leal on 2019-10-19.
 */

object AppConverters {
    @JvmStatic
    @TypeConverter
    fun fromTimestamp(value: Long): Date = Date(value)

    @JvmStatic
    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time
}