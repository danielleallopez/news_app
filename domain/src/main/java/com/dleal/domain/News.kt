package com.dleal.domain

/**
 * Created by Daniel Leal on 2019-10-19.
 */
data class Article(
    override val id: Long,
    val imageUrl: String,
    val headline: String,
    val description: String,
    val url: String,
    val tags: List<String>,
    override val isPremium: Boolean
): NewsElement

data class Video(
    override val id: Long,
    val imageUrl: String,
    val headline: String,
    val type: String,
    val duration: Int,
    val url: String,
    override val isPremium: Boolean
) : NewsElement

interface NewsElement{
    val id: Long
    val isPremium: Boolean
}