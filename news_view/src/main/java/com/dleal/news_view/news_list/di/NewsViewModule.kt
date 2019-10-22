package com.dleal.news_view.news_list.di

import com.dleal.news_view.news_list.ui.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Daniel Leal on 2019-10-21.
 */
val newsViewModule = module {
    viewModel {
        NewsListViewModel(get(), get())
    }
}