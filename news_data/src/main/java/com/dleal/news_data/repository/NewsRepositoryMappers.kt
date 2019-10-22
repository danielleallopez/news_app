package com.dleal.news_data.repository

import com.dleal.domain.Article
import com.dleal.domain.NewsElement
import com.dleal.domain.Video
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

fun ArticleDto.toData() =
    ArticleData(
        id = id,
        imageUrl = imageUrl,
        headline = headline,
        description = description,
        url = url,
        tags = tags,
        isPremium = isPremium
    )

fun VideoDto.toData() =
    VideoData(
        id = id,
        imageUrl = imageUrl,
        headline = headline,
        type = type,
        duration = duration,
        url = url,
        isPremium = isPremium
    )

fun NewsElementData.toDomain(): NewsElement {
    return when (this) {
        is ArticleData -> this.toDomain()
        is VideoData -> this.toDomain()
        else -> throw IllegalArgumentException("Unmapped NewsElementData!")
    }
}

fun ArticleData.toDomain() =
    Article(
        id = id,
        imageUrl = imageUrl,
        headline = headline,
        description = description,
        url = url,
        tags = tags,
        isPremium = isPremium
    )

fun VideoData.toDomain() =
    Video(
        id = id,
        imageUrl = imageUrl,
        headline = headline,
        type = type,
        duration = duration,
        url = url,
        isPremium = isPremium
    )