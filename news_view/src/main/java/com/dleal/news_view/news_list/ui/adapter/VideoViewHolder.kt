package com.dleal.news_view.news_list.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dleal.core.utils.hide
import com.dleal.core.utils.show
import com.dleal.domain.Video
import com.dleal.news_view.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_headline.view.*
import kotlinx.android.synthetic.main.item_video_element.view.*

/**
 * Created by Daniel Leal on 2019-10-22.
 */
class VideoViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private val layoutPremium by lazy { containerView.layout_premium }
    private val imgPlayIcon by lazy { containerView.img_play_video }
    private val txtHeadline by lazy { containerView.txt_headline }
    private val imgVideoImage by lazy { containerView.img_video_image }
    private val txtVideoDuration by lazy { containerView.txt_video_duration }

    fun bind(
        video: Video,
        listener: (Video) -> Unit
    ) {
        processIsPremium(video)
        processHeadline(video)
        processVideoLength(video)
        processImage(video)

        imgPlayIcon.setOnClickListener { listener(video) }
    }

    private fun processIsPremium(video: Video) {
        when {
            video.isPremium -> layoutPremium.show()
            else -> layoutPremium.hide()
        }
    }

    private fun processHeadline(video: Video) {
        txtHeadline.text = video.headline.capitalize()
    }

    private fun processVideoLength(video: Video) {
        txtVideoDuration.text = formatSeconds(video.duration)
    }

    private fun processImage(video: Video) {
        Glide.with(containerView)
            .load(video.imageUrl)
            .placeholder(R.drawable.ic_placeholder_video)
            .into(imgVideoImage)
    }

    private fun formatSeconds(timeInSeconds: Int): String {
        val minutes = String.format("%02d", timeInSeconds / 60 % 60)
        val seconds = String.format("%02d", timeInSeconds % 60)
        return "$minutes : $seconds"
    }
}