package com.dleal.news_data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel Leal on 2019-10-19.
 */
abstract class NewsElementData(
    @PrimaryKey @SerializedName("id") val id: Long,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("headline") val headline: String,
    @SerializedName("url") val url: String,
    @SerializedName("isPremium") val isPremium: Boolean
)

@Entity(tableName = TABLE_NAME_ARTICLES)
class ArticleData(
    id: Long,
    imageUrl: String,
    headline: String,
    @SerializedName("description") val description: String,
    url: String,
    @SerializedName("tags") val tags: List<String>,
    isPremium: Boolean
) : NewsElementData(id, imageUrl, headline, url, isPremium)

@Entity(tableName = TABLE_NAME_VIDEOS)
class VideoData(
    id: Long,
    imageUrl: String,
    headline: String,
    @SerializedName("type") val type: String,
    @SerializedName("duration") val duration: Int,
    url: String,
    isPremium: Boolean
) : NewsElementData(id, imageUrl, headline, url, isPremium)