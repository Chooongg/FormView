package com.chooongg.formView.layoutManager

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chooongg.formView.FormView
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.part.AbstractFormPart
import com.google.android.material.progressindicator.LinearProgressIndicator

class FormItemDecoration(context: Context) : ItemDecoration() {

    val progress = LinearProgressIndicator(context)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent !is FormView) return
        val adapter = parent.adapter ?: return
        if (adapter.data.showProgress) {
            progress.layoutParams = GridLayoutManager.LayoutParams(c.width, -2)
            progress.progress = 50
            progress.max = 100
            progress.draw(c)
        }
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        if (parent !is FormView) return
        val holderTemp = parent.getChildViewHolder(view)
        val holder = holderTemp as? FormViewHolder ?: return
        if (holder.absoluteAdapterPosition == RecyclerView.NO_POSITION) return
        val adapter = parent.adapter ?: return
        val pair = adapter.data.getWrappedAdapterAndPosition(holder.absoluteAdapterPosition)
        if (pair.first !is AbstractFormPart<*>) return
        val item = adapter.getItem(holder.absoluteAdapterPosition) ?: return
        val startSize = holder.style.padding.start - holder.style.padding.startMedium
        val endSize = holder.style.padding.end - holder.style.padding.endMedium
        val start = when (item.boundary.start) {
            FormBoundary.GLOBAL -> if (holder.style.isFillHorizontalMargin()) 0 else -startSize
            FormBoundary.MIDDLE -> if (holder.style.isIndependentItem) {
                (startSize + endSize) / item.columnCount * item.columnIndex
            } else holder.style.margin.startMedium - if (holder.style.isFillHorizontalMargin()) 0 else startSize

            else -> 0
        }
        val end = when (item.boundary.end) {
            FormBoundary.GLOBAL -> if (holder.style.isFillHorizontalMargin()) 0 else -endSize
            FormBoundary.MIDDLE -> if (holder.style.isIndependentItem) {
                (start + endSize) / item.columnCount * (item.columnCount - 1 - item.columnIndex)
            } else holder.style.margin.endMedium - if (holder.style.isFillHorizontalMargin()) 0 else endSize

            else -> 0
        }
        val topSize = holder.style.padding.top - holder.style.padding.topMedium
        val top = when (item.boundary.top) {
            FormBoundary.GLOBAL -> if (holder.style.isFillVerticalMargin()) 0 else -topSize
            FormBoundary.MIDDLE -> if (holder.style.isFillVerticalMargin()) holder.style.margin.topMedium else 0
            else -> 0
        }
        val bottomSize = holder.style.padding.bottom - holder.style.padding.bottomMedium
        val bottom = when (item.boundary.bottom) {
            FormBoundary.GLOBAL -> if (holder.style.isFillVerticalMargin()) 0 else -bottomSize
            FormBoundary.MIDDLE -> if (holder.style.isFillVerticalMargin()) holder.style.margin.bottomMedium else 0
            else -> 0
        }
        outRect.top = top
        outRect.bottom = bottom
        if (view.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            outRect.left = end
            outRect.right = start
        } else {
            outRect.left = start
            outRect.right = end
        }
    }
}