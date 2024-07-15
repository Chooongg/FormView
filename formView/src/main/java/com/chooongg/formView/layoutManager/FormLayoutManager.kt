package com.chooongg.formView.layoutManager

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.Px
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormAdapter
import com.chooongg.formView.FormManager
import com.chooongg.formView.part.AbstractFormPart
import kotlin.math.max
import kotlin.math.min

class FormLayoutManager internal constructor(context: Context, fixedColumn: Int, columnWidth: Int) :
    GridLayoutManager(context, FormManager.FORM_SPAN_COUNT) {

    @IntDef(-1)
    annotation class ColumnNullValue

    internal var adapter: FormAdapter? = null

    @ColumnNullValue
    @IntRange(1, FormManager.FORM_MAX_COLUMN_COUNT.toLong())
    var columnCount: Int = -1
        private set(value) {
            if (field == value) return
            field = value
            adapter?.columnCount = value
            adapter?.update()
        }

    @ColumnNullValue
    @IntRange(1, FormManager.FORM_MAX_COLUMN_COUNT.toLong())
    var fixedColumn: Int = fixedColumn
        internal set(value) {
            if (field == value) return
            if (value == -1 && columnWidth < -1) {
                field = 1
            } else {
                field = value
                columnWidth = -1
            }
            updateColumn()
        }

    @Px
    var columnWidth: Int = columnWidth
        internal set(value) {
            if (field == value) return
            field = value
            fixedColumn = if (value <= 0) 1 else -1
            updateColumn()
        }


    private var padding: Rect = Rect()

    init {
        spanSizeLookup = FormSpanSizeLookup()
    }

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        padding = Rect(left, top, right, bottom)
    }

    override fun getPaddingTop(): Int = max(0, padding.top - 1)
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
        updateColumn()
    }

    private fun updateColumn() {
        columnCount = if (fixedColumn > 0) {
            fixedColumn
        } else {
            max(1, min(10, (width - paddingLeft - paddingRight) / columnWidth))
        }
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

    private inner class FormSpanSizeLookup : SpanSizeLookup() {

        init {
            isSpanIndexCacheEnabled = false
        }

        override fun invalidateSpanIndexCache() {
            super.invalidateSpanIndexCache()
            adapter?.data?.concatAdapter?.adapters?.forEach {
                if (it is FormCustomSpanLookup) it.invalidateSpanIndexCache()
            }
        }

        override fun getSpanSize(position: Int): Int {
            val pair = adapter?.data?.getWrappedAdapterAndPosition(position) ?: return spanCount
            val size = when (val part = pair.first) {
                is AbstractFormPart<*> -> spanCount / part[pair.second].columnCount * part[pair.second].columnSize
                is FormCustomSpanLookup -> part.getSpanSize(position, columnCount)
                else -> spanCount
            }
            return size
        }

        override fun getSpanIndex(position: Int, spanCount: Int): Int {
            val pair = adapter?.data?.getWrappedAdapterAndPosition(position) ?: return spanCount
            val index = when (val part = pair.first) {
                is AbstractFormPart<*> -> spanCount / part[pair.second].columnCount * part[pair.second].columnIndex
                is FormCustomSpanLookup -> part.getSpanIndex(position, columnCount)
                else -> 0
            }
            return index
        }
    }
}
