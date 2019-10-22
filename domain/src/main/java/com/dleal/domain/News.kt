package com.dleal.domain

/**
 * Created by Daniel Leal on 2019-10-19.
 */
data class Article(
    override val id: Long,
    override val imageUrl: String,
    override val headline: String,
    val description: String,
    override val url: String,
    val tags: List<String>,
    override val isPremium: Boolean
): NewsElement

data class Video(
    override val id: Long,
    override val imageUrl: String,
    override val headline: String,
    val type: String,
    val duration: Int,
    override val url: String,
    override val isPremium: Boolean
) : NewsElement

interface NewsElement{
    val id: Long
    val imageUrl: String
    val headline: String
    val url: String
    val isPremium: Boolean
}