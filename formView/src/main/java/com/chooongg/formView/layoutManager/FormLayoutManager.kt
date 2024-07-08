package com.chooongg.formView.layoutManager

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormManager
import com.google.android.flexbox.FlexboxLayoutManager

class FormLayoutManager(context: Context) : FlexboxLayoutManager(context) {

    private var padding: Rect = Rect()

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        padding = Rect(left, top, right, bottom)
    }

    override fun getPaddingTop(): Int {
        return padding.top
    }

    override fun getPaddingStart(): Int {
        return if (layoutDirection == View.LAYOUT_DIRECTION_RTL) padding.right else padding.left
    }

    override fun getPaddingEnd(): Int {
        return if (layoutDirection == View.LAYOUT_DIRECTION_RTL) padding.left else padding.right
    }

    override fun getPaddingLeft(): Int {
        return padding.left
    }

    override fun getPaddingRight(): Int {
        return padding.right
    }

    override fun getPaddingBottom(): Int {
        return padding.bottom
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView, state: RecyclerView.State, position: Int
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
