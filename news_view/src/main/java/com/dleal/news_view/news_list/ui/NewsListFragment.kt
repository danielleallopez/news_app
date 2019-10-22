package com.dleal.news_view.news_list.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dleal.core.ui.base.BaseFragment
import com.dleal.core.utils.CLogger
import com.dleal.core.utils.show
import com.dleal.news_view.R
import com.dleal.news_view.news_list.ui.adapter.NewsElementAdapter
import com.dleal.news_view.news_list.ui.adapter.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Daniel Leal on 2019-10-21.
 */
class NewsListFragment : BaseFragment<NewsListViewModel>(), CLogger {

    override val viewModel: NewsListViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_news_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureList()

        subscribeToListEvents()

        viewModel.start()
    }

    private fun subscribeToListEvents() {
        viewModel.newsElementsEvents().observe(this, Observer { elementList ->

            refresh_news_elements.show()
            refresh_news_elements.isRefreshing = false
            (rv_news_elements.adapter as NewsElementAdapter).updateList(elementList)
        })
    }

    private fun configureList() {
        val newsElementAdapter = NewsElementAdapter { newsElement ->
            //TODO: openDetailView(newsElement)
        }

        rv_news_elements.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager

            addItemDecoration(
                VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.list_item_separation))
            )

            adapter = newsElementAdapter
        }

        refresh_news_elements.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }
}