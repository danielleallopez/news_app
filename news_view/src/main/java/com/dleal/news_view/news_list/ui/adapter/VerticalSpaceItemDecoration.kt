package com.dleal.news_view.news_list.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Daniel Leal on 2019-10-22.
 */
class VerticalSpaceItemDecoration(private val spaceInPixels: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceInPixels
        }
        outRect.bottom = spaceInPixels
    }
}