package com.dleal.core.utils

import android.util.Log

interface CLogger {

    private val TAG: String
        get() = javaClass.simpleName

    fun logDebug(message: String) = Log.d(TAG, message)
    fun logWarning(message: String) = Log.w(TAG, message)
    fun logError(message: String) = Log.e(TAG, message)
    fun logInfo(message: String) = Log.i(TAG, message)
}