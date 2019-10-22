package com.dleal.news_view.news_list.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dleal.core.utils.hide
import com.dleal.core.utils.show
import com.dleal.domain.Article
import com.dleal.news_view.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_headline.view.*
import kotlinx.android.synthetic.main.item_article_element.view.*

/**
 * Created by Daniel Leal on 2019-10-22.
 */
class ArticleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private val layoutPremium by lazy { containerView.layout_premium }
    private val imgArticleImage by lazy { containerView.img_article_image }
    private val txtArticleHeadline by lazy { containerView.txt_headline }
    private val txtArticleDescription by lazy { containerView.txt_article_description }

    fun bind(
        article: Article,
        listener: (Article) -> Unit
    ) {
        processIsPremium(article)
        processImage(article)
        processHeadline(article)
        processDescription(article)

        containerView.setOnClickListener { listener(article) }
    }

    private fun processIsPremium(article: Article) {
        when {
            article.isPremium -> layoutPremium.show()
            else -> layoutPremium.hide()
        }
    }

    private fun processImage(article: Article) {
        Glide.with(containerView)
            .load(article.imageUrl)
            .placeholder(R.drawable.ic_placeholder_article)
            .into(imgArticleImage)
    }

    private fun processHeadline(article: Article) {
        txtArticleHeadline.text = article.headline
    }

    private fun processDescription(article: Article) {
        txtArticleDescription.text = article.description
    }
}