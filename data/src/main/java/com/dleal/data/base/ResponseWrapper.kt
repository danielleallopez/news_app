package com.dleal.data.base

import com.google.gson.annotations.SerializedName

sealed class ResponseWrapper<Payload>

data class Success<Payload>(val payload: Payload?) : ResponseWrapper<Payload>()

data class Failure<Payload>(
    @SerializedName("code") val code: Int = INVALID_CODE,
    @SerializedName("status") val status: String = "",
    @SerializedName("exception") val exception: String = "",
    @SerializedName("message") val message: String = ""
) : ResponseWrapper<Payload>()

class NoInternet<Payload> : ResponseWrapper<Payload>()

const val INVALID_CODE = -1