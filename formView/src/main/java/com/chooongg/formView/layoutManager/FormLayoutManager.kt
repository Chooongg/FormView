package com.chooongg.formView.layoutManager

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormAdapter
import com.chooongg.formView.FormManager
import com.chooongg.formView.part.AbstractFormPart
import kotlin.math.max
import kotlin.math.min

class FormLayoutManager internal constructor(context: Context, formColumn: Int) :
    GridLayoutManager(context, FormManager.FORM_SPAN_COUNT) {

    private var adapter: FormAdapter? = null

    @IntRange(from = 1, to = FormManager.FORM_COLUMN_COUNT.toLong())
    var formColumn: Int = min(max(formColumn, 1), FormManager.FORM_COLUMN_COUNT)
        set(value) {
            adapter?.columnCount = value
            if (field != value) {
                field = value
                adapter?.update()
            }
        }

    private var padding: Rect = Rect()

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun invalidateSpanIndexCache() {
                super.invalidateSpanIndexCache()
                adapter?.data?.concatAdapter?.adapters?.forEach {
                    if (it is FormCustomSpanLookup) it.invalidateSpanIndexCache()
                }
            }

            override fun getSpanSize(position: Int): Int {
                val pair = adapter?.data?.getWrappedAdapterAndPosition(position) ?: return spanCount
                return when (val part = pair.first) {
//                    is AbstractFormPart<*> -> spanCount / part[pair.second].columnSize * part[pair.second].columnSize
                    is AbstractFormPart<*> -> part[pair.second].spanSize
                    is FormCustomSpanLookup -> part.getSpanSize(position, formColumn)
                    else -> spanCount
                }
            }

            override fun getSpanIndex(position: Int, spanCount: Int): Int {
                val pair = adapter?.data?.getWrappedAdapterAndPosition(position) ?: return spanCount
                return when (val part = pair.first) {
//                    is AbstractFormPart<*> -> spanCount / part[pair.second].columnSize * part[pair.second].columnIndex
                    is AbstractFormPart<*> -> part[pair.second].spanIndex
                    is FormCustomSpanLookup -> part.getSpanIndex(position, formColumn)
                    else -> 0
                }
            }
        }
    }

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        padding = Rect(left, top, right, bottom)
    }

    override fun getPaddingTop(): Int = padding.top
    override fun getPaddingBottom(): Int = padding.bottom
    override fun getPaddingLeft(): Int = padding.left
    override fun getPaddingRight(): Int = padding.right

    override fun getPaddingStart(): Int =
        if (layoutDirection == View.LAYOUT_DIRECTION_RTL) padding.right else padding.left

    override fun getPaddingEnd(): Int =
        if (layoutDirection == View.LAYOUT_DIRECTION_RTL) padding.left else padding.right

    override fun onMeasure(
        recycler: RecyclerView.Recycler, state: RecyclerView.State, widthSpec: Int, heightSpec: Int
    ) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
    }

    override fun onAttachedToWindow(recyclerView: RecyclerView) {
        adapter = recyclerView.adapter as? FormAdapter
        super.onAttachedToWindow(recyclerView)
    }

    override fun onDetachedFromWindow(view: RecyclerView, recycler: RecyclerView.Recycler) {
        super.onDetachedFromWindow(view, recycler)
        adapter = null
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
