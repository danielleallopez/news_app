package com.dleal.news_data.local

import com.dleal.data.datasources.local.BaseLocalDataSource
import com.dleal.news_data.local.database.ArticleData
import com.dleal.news_data.local.database.NewsDatabase
import com.dleal.news_data.local.database.NewsElementData
import com.dleal.news_data.local.database.VideoData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by Daniel Leal on 2019-10-20.
 */
class NewsLocalDataSource(private val newsDatabase: NewsDatabase) : BaseLocalDataSource() {
    private val newsDao by lazy { newsDatabase.getNewsDao() }

    fun deleteNewsElements() {
        Completable.fromAction { newsDao.deleteAllArticles() }
            .concatWith { newsDao.deleteAllVideos() }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun insertNewsElements(newsElementList: List<NewsElementData>) {
        newsElementList.forEach { element ->
            when (element) {
                is ArticleData -> newsDao.insertArticle(element)
                is VideoData -> newsDao.insertVideo(element)
            }
        }
    }

    fun getAllNewsElements(): Flowable<List<NewsElementData>> {
        val fetchArticles = newsDao.getAllArticles().toFlowable().onErrorReturnItem(emptyList())
        val fetchVideos = newsDao.getAllVideos().toFlowable().onErrorReturnItem(emptyList())
        return Flowable.zip(
            fetchArticles,
            fetchVideos,
            BiFunction { articles, videos ->
                mutableListOf<NewsElementData>().run {
                    addAll(articles)
                    addAll(videos)
                    sortBy { it.id }
                    toList()
                }
            })
    }
}