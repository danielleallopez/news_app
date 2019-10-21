package com.dleal.news_view.news_list.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dleal.core.ui.base.BaseFragment
import com.dleal.core.utils.CLogger
import com.dleal.news_view.R

import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Daniel Leal on 2019-10-21.
 */
class NewsListFragment : BaseFragment<NewsListViewModel>(), CLogger {

    override val viewModel: NewsListViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_news_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToListEvents()

        viewModel.start()
    }

    private fun subscribeToListEvents() {
        viewModel.newsElementsEvents().observe(this, Observer { elementList ->
            elementList.forEach {
                logDebug("Element id is ${it.id}, with url: ${it.url}")
            }
        })
    }
}