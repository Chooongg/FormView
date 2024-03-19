package com.chooongg.form.layoutManager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.FormManager
import com.google.android.flexbox.FlexboxLayoutManager

abstract class AbstractFormLayoutManager(context: Context) : FlexboxLayoutManager(context) {

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        if (FormManager.centerSmoothScroll) {
            startSmoothScroll(CenterSmoothScroller(recyclerView.context).apply {
                targetPosition = position
            })
        } else super.smoothScrollToPosition(recyclerView, state, position)
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            flexGrow = 1f
        }
    }

    override fun generateLayoutParams(
        c: Context?,
        attrs: AttributeSet?
    ): RecyclerView.LayoutParams {
        return LayoutParams(c, attrs).apply {
            flexGrow = 1f
        }
    }

    private class CenterSmoothScroller(context: Context) : LinearSmoothScroller(context) {
        override fun calculateDtToFit(
            viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int
        ) = (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2)
    }
}