package com.chooongg.formView.layoutManager

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormAdapter
import com.chooongg.formView.FormManager
import com.chooongg.ktx.logE
import com.google.android.flexbox.FlexInternalFunction
import com.google.android.flexbox.FlexboxLayoutManager

class FormLayoutManager(context: Context) : FlexboxLayoutManager(context) {

    private var _recyclerView: RecyclerView? = null

    private var padding: Rect = Rect()

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        val adapter = _recyclerView?.adapter as? FormAdapter ?: return
        for (position in 0 until state.itemCount) {
            val item = adapter.getItem(position) ?: continue
            val lineIndex = FlexInternalFunction.getPositionToFlexLineIndex(this, position)
//            logE("Form", "Position: ${position}, Line: ${lineIndex}")
        }
    }

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

    override fun onAttachedToWindow(recyclerView: RecyclerView) {
        _recyclerView = recyclerView
        super.onAttachedToWindow(recyclerView)
    }

    override fun onDetachedFromWindow(view: RecyclerView, recycler: RecyclerView.Recycler) {
        super.onDetachedFromWindow(view, recycler)
        _recyclerView = null
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
