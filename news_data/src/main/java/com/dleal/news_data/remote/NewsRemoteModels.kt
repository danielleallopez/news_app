package com.dleal.news_data.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel Leal on 2019-10-21.
 */
abstract class NewsElementDto(
    @SerializedName("id") val id: Long,
    @SerializedName("isPremium") val isPremium: Boolean
)

class ArticleDto(
    id: Long,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("headline") val headline: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("tags") val tags: List<String>,
    isPremium: Boolean
) : NewsElementDto(id, isPremium)

class VideoDto(
    id: Long,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("headline") val headline: String,
    @SerializedName("type") val type: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("url") val url: String,
    isPremium: Boolean
) : NewsElementDto(id, isPremium)