package com.dleal.news_data.repository

import com.dleal.news_data.local.NewsLocalDataSource
import com.dleal.news_data.local.database.NewsElementData
import com.dleal.news_data.remote.NewsRemoteDataSource
import io.reactivex.Flowable

/**
 * Created by Daniel Leal on 2019-10-20.
 */
class NewsRepository(
    private val local: NewsLocalDataSource,
    private val remote: NewsRemoteDataSource
) {

    fun fetchNewsElements(page: Int, pageSize: Int): Flowable<List<NewsElementData>> {
        if (page == INITIAL_PAGE) {
            local.deleteNewsElements()
        }
        return remote.fetchNewsElements(page, pageSize)
            .toFlowable()
            .map {
                val elementsDataList = it.map { dto -> dto.toData() }
                local.insertNewsElements(elementsDataList)
                elementsDataList
            }
            .startWith(local.getAllNewsElements())
    }
}

private const val INITIAL_PAGE = 0