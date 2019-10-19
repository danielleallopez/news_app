package com.dleal.data.util

fun Boolean?.orDefault(default: Boolean = DEFAULT_BOOLEAN) = this ?: default
fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true

fun Int?.orDefault(default: Int = DEFAULT_INT) = this ?: default
fun Long?.orDefault(default: Long = DEFAULT_LONG) = this ?: default
fun Double?.orDefault(default: Double = DEFAULT_DOUBLE) = this ?: default
fun Float?.orDefault(default: Float = DEFAULT_FLOAT) = this ?: default
fun String?.orDefault(default: String = DEFAULT_STRING) : String = this ?: default
fun <T> List<T>?.orDefault(default: List<T> = listOf()) = this ?: default

const val DEFAULT_BOOLEAN = false
const val DEFAULT_INT = -1
const val DEFAULT_LONG = -1L
const val DEFAULT_DOUBLE = 0.0
const val DEFAULT_FLOAT = -1f
const val DEFAULT_STRING = ""