package com.dleal.news_data.local

import com.dleal.data.datasources.local.BaseLocalDataSource
import com.dleal.news_data.local.database.ArticleData
import com.dleal.news_data.local.database.NewsDatabase
import com.dleal.news_data.local.database.NewsElementData
import com.dleal.news_data.local.database.VideoData
import io.reactivex.Single

/**
 * Created by Daniel Leal on 2019-10-20.
 */
class NewsLocalDataSource(private val newsDatabase: NewsDatabase) : BaseLocalDataSource() {
    private val newsDao by lazy { newsDatabase.getNewsDao() }

    fun deleteNewsElements() {
        newsDao.deleteAllArticles()
    }

    fun insertNewsElements(newsElementList: List<NewsElementData>) {
        newsElementList.forEach { element ->
            when (element) {
                is ArticleData -> newsDao.insertArticle(element)
                is VideoData -> newsDao.insertVideo(element)
            }
        }
    }

    fun getAllNewsElements(): Single<List<NewsElementData>> {
        val articles = newsDao.getAllArticles()
        val videos = newsDao.getAllVideos()
        return Single.just(articles.union(videos).shuffled())
    }
}