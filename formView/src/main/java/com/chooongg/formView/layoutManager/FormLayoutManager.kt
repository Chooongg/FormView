package com.chooongg.formView.layoutManager

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormManager
import com.google.android.flexbox.FlexboxLayoutManager

class FormLayoutManager(context: Context) : FlexboxLayoutManager(context) {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return super.generateDefaultLayoutParams()
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return super.generateLayoutParams(lp)
    }

    override fun generateLayoutParams(
        c: Context?,
        attrs: AttributeSet?
    ): RecyclerView.LayoutParams {
        return super.generateLayoutParams(c, attrs)
    }

    override fun getPaddingTop(): Int {
        return super.getPaddingTop()
    }

    override fun getPaddingStart(): Int {
        return super.getPaddingStart()
    }

    override fun getPaddingEnd(): Int {
        return super.getPaddingEnd()
    }

    override fun getPaddingLeft(): Int {
        return super.getPaddingLeft()
    }

    override fun getPaddingRight(): Int {
        return super.getPaddingRight()
    }

    override fun getPaddingBottom(): Int {
        return super.getPaddingBottom()
    }

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

    private class CenterSmoothScroller(context: Context) : LinearSmoothScroller(context) {
        override fun calculateDtToFit(
            viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int
        ) = (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2)
    }
}
