package com.dleal.news_data.local.database

import androidx.room.*

/**
 * Created by Daniel Leal on 2019-10-19.
 */
@Dao
interface NewsDao {
    //Articles
    @Query("SELECT * FROM $TABLE_NAME_ARTICLES")
    fun getAllArticles(): List<ArticleData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: ArticleData)

    @Delete
    fun deleteArticle(article: ArticleData)

    @Query("DELETE FROM $TABLE_NAME_ARTICLES")
    fun deleteAllArticles()

    @Query("DELETE FROM $TABLE_NAME_ARTICLES WHERE id = :id")
    fun deleteArticleById(id: Long)

    //Videos
    @Query("SELECT * FROM $TABLE_NAME_VIDEOS")
    fun getAllVideos(): List<VideoData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(video: VideoData)

    @Delete
    fun deleteVideo(video: VideoData)

    @Query("DELETE FROM $TABLE_NAME_VIDEOS")
    fun deleteAllVideos()

    @Query("DELETE FROM $TABLE_NAME_VIDEOS WHERE id = :id")
    fun deleteVideoById(id: Long)
}

const val TABLE_NAME_ARTICLES = "Articles"
const val TABLE_NAME_VIDEOS = "Videos"