package com.dleal.news_view.news_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dleal.domain.Article
import com.dleal.domain.NewsElement
import com.dleal.domain.Video
import com.dleal.news_view.R


/**
 * Created by Daniel Leal on 2019-10-22.
 */
class NewsElementAdapter(private val listener: (NewsElement) -> Unit) :
    ListAdapter<NewsElement, RecyclerView.ViewHolder>(Diff()) {

    private var newsElementList: List<NewsElement> = emptyList()
        set(value) {
            field = value

            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIDEO -> {
                VideoViewHolder(parent.inflateView(R.layout.item_video_element))
            }
            ARTICLE -> {
                ArticleViewHolder(parent.inflateView(R.layout.item_article_element))
            }
            else -> throw IllegalArgumentException("Unsupported view type $viewType")
        }

    override fun getItemCount(): Int = newsElementList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = newsElementList[position]
        when (holder) {
            is VideoViewHolder -> {
                holder.bind(element as Video, listener)
            }
            is ArticleViewHolder -> {
                holder.bind(element as Article, listener)
            }
        }
    }

    override fun getItemViewType(position: Int) =
        when (val element = newsElementList[position]) {
            is Video -> VIDEO
            is Article -> ARTICLE
            else -> throw IllegalArgumentException("Unsupported view type for ${element.javaClass.simpleName}")
        }

    fun updateList(list: List<NewsElement>) {
        newsElementList = list
    }

    private fun ViewGroup.inflateView(@LayoutRes layoutId: Int) =
        LayoutInflater.from(context).inflate(layoutId, this, false)
}

class Diff : DiffUtil.ItemCallback<NewsElement>() {
    override fun areItemsTheSame(oldItem: NewsElement, newItem: NewsElement) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NewsElement, newItem: NewsElement) = oldItem == newItem
}

private const val VIDEO = 1
private const val ARTICLE = 2