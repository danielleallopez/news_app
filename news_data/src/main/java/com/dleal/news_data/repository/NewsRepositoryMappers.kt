package com.dleal.news_data.repository

import com.dleal.news_data.local.database.ArticleData
import com.dleal.news_data.local.database.NewsElementData
import com.dleal.news_data.local.database.VideoData
import com.dleal.news_data.remote.ArticleDto
import com.dleal.news_data.remote.NewsElementDto
import com.dleal.news_data.remote.VideoDto

/**
 * Created by Daniel Leal on 2019-10-21.
 */
fun NewsElementDto.toData(): NewsElementData {
    return when (this) {
        is ArticleDto -> this.toData()
        is VideoDto -> this.toData()
        else -> throw IllegalArgumentException("Unmapped NewsElement!")
    }
}

fun ArticleDto.toData(): ArticleData =
    ArticleData(
        id = id,
        imageUrl = imageUrl,
        headline = headline,
        description = description,
        url = url,
        tags = tags,
        isPremium = isPremium
    )

fun VideoDto.toData(): VideoData =
    VideoData(
        id = id,
        imageUrl = imageUrl,
        headline = headline,
        type = type,
        duration = duration,
        url = url,
        isPremium = isPremium
    )