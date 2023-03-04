package com.example.structure.ui.common

import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView

class VerticalMarginItemDecoration(
    @IntRange(from = 0) private val margin: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildAdapterPosition(view)) {
//            0 -> {
//                outRect.top = marginTop
//            }
//            (itemCount - 1) -> {
//                outRect.bottom = marginBottom
//            }
            else -> {
                outRect.top = (margin / 2)
                outRect.bottom = (margin / 2)
            }
        }
    }
}