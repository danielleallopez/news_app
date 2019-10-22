package com.dleal.news_data.usecase

import com.dleal.news_data.repository.NewsRepository
import com.dleal.news_data.repository.toDomain

/**
 * Created by Daniel Leal on 2019-10-21.
 */
class FetchNewsUseCase(private val newsRepository: NewsRepository) {

    fun getNews(page: Int, pageSize: Int) =
        newsRepository.fetchNewsElements(page, pageSize)
            .map {dataList ->
                dataList.map { it.toDomain() }
            }
}