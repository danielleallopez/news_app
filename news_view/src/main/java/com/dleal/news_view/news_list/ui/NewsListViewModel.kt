package com.dleal.news_view.news_list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dleal.core.ui.base.BaseViewModel
import com.dleal.core.utils.RxTransformer
import com.dleal.domain.NewsElement
import com.dleal.news_data.usecase.FetchNewsUseCase

/**
 * Created by Daniel Leal on 2019-10-21.
 */
class NewsListViewModel(
    private val fetchNewsUseCase: FetchNewsUseCase,
    override val rxTransformer: RxTransformer
) : BaseViewModel() {

    private val newsElements = MutableLiveData<List<NewsElement>>()

    fun newsElementsEvents(): LiveData<List<NewsElement>> = newsElements

    fun start() {
        fetchPage(INITIAL_PAGE, PAGE_SIZE)
    }

    private fun fetchPage(page: Int, pageSize: Int) {
        addDisposable(
            fetchNewsUseCase.getNews(page, pageSize)
                .compose(rxTransformer.applyIoSchedulerToFlow())
                .subscribe(
                    { list: List<NewsElement> ->
                        newsElements.value = list
                    },
                    { throwable: Throwable? ->
                        logError(throwable?.message ?: "No error message")
                        throwable?.printStackTrace()
                    }
                )
        )
    }

    fun onRefresh() {
        fetchPage(INITIAL_PAGE, PAGE_SIZE)
    }
}

private const val INITIAL_PAGE = 0
private const val PAGE_SIZE = 10