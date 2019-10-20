package com.dleal.news_data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel Leal on 2019-10-19.
 */
abstract class NewsElementData(
    @PrimaryKey @SerializedName("id") val id: Long,
    @SerializedName("isPremium") val isPremium: Boolean
)

@Entity(tableName = TABLE_NAME_ARTICLES)
class ArticleData(
    id: Long,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("headline") val headline: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("tags") val tags: List<String>,
    isPremium: Boolean
) : NewsElementData(id, isPremium)

@Entity(tableName = TABLE_NAME_VIDEOS)
class VideoData(
    id: Long,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("headline") val headline: String,
    @SerializedName("type") val type: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("url") val url: String,
    isPremium: Boolean
) : NewsElementData(id, isPremium)