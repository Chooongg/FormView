package com.chooongg.formView.decoration

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMarginsRelative
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormView
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.part.AbstractFormPart

class FormItemDecoration : ItemDecoration() {

    @Suppress("LocalVariableName")
    @SuppressLint("SwitchIntDef")
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        if (parent !is FormView) return
        val holder = parent.getChildViewHolder(view) as? FormItemViewHolder ?: return
        if (holder.absoluteAdapterPosition == RecyclerView.NO_POSITION) return
        val adapter = parent.adapter ?: return
        val pair = adapter.data.getWrappedAdapterAndPosition(holder.absoluteAdapterPosition)
        if (pair.first !is AbstractFormPart<*>) return
        val item = adapter.getItem(holder.absoluteAdapterPosition) ?: return
        var _marginStart = 0
        var _marginTop = 0
        var _marginEnd = 0
        var _marginBottom = 0
        var _paddingStart = 0
        var _paddingTop = 0
        var _paddingEnd = 0
        var _paddingBottom = 0
        val isAlignmentToEdge = holder.style.isAlignmentToEdge()
        val columns = (pair.first as AbstractFormPart<*>).columnCount
        val index = item.spanIndex / (FormManager.FORM_SPAN_COUNT / columns)
        val start = holder.style.paddingInfo.start - holder.style.paddingInfo.startMedium
        val end = holder.style.paddingInfo.end - holder.style.paddingInfo.endMedium
        when (item.boundary.start) {
            FormBoundary.GLOBAL -> {
                _paddingStart = if (item.fillEdgesPadding && !isAlignmentToEdge) start else 0
                _marginStart = if (isAlignmentToEdge) -start else 0
            }

            FormBoundary.MIDDLE -> {
                _paddingStart = if (item.fillEdgesPadding && !isAlignmentToEdge) start else 0
                _marginStart = if (holder.style.config.isIndependentItem) {
                    (start + end) / columns * index
                } else holder.style.marginInfo.startMedium - if (isAlignmentToEdge) start else 0
            }
        }
        when (item.boundary.end) {
            FormBoundary.GLOBAL -> {
                _paddingEnd = if (item.fillEdgesPadding && !isAlignmentToEdge) end else 0
                _marginEnd = if (isAlignmentToEdge) -end else 0
            }

            FormBoundary.MIDDLE -> {
                _paddingEnd = if (item.fillEdgesPadding && !isAlignmentToEdge) end else 0
                _marginEnd = if (holder.style.config.isIndependentItem) {
                    (start + end) / columns * (columns - 1 - index)
                } else holder.style.marginInfo.endMedium - if (isAlignmentToEdge) end else 0
            }
        }
        val top = holder.style.paddingInfo.top - holder.style.paddingInfo.topMedium
        when (item.boundary.top) {
            FormBoundary.GLOBAL -> {
                _paddingTop = if (item.fillEdgesPadding && !isAlignmentToEdge) top else 0
                _marginTop = if (isAlignmentToEdge) -top else 0
            }

            FormBoundary.MIDDLE -> {
                _paddingTop = if (item.fillEdgesPadding && !isAlignmentToEdge) top else 0
                _marginTop = if (isAlignmentToEdge) 0 else holder.style.marginInfo.topMedium
            }
        }
        val bottom = holder.style.paddingInfo.bottom - holder.style.paddingInfo.bottomMedium
        when (item.boundary.bottom) {
            FormBoundary.GLOBAL -> {
                _paddingBottom = if (item.fillEdgesPadding && !isAlignmentToEdge) bottom else 0
                _marginBottom = if (isAlignmentToEdge) -bottom else 0
            }

            FormBoundary.MIDDLE -> {
                _paddingBottom = if (item.fillEdgesPadding && !isAlignmentToEdge) bottom else 0
                _marginBottom = if (isAlignmentToEdge) 0 else holder.style.marginInfo.bottomMedium
            }
        }
        holder.itemView.setPaddingRelative(_paddingStart, _paddingTop, _paddingEnd, _paddingBottom)
        holder.itemView.updateLayoutParams<MarginLayoutParams> {
            marginStart
            updateMarginsRelative(_marginStart, _marginTop, _marginEnd, _marginBottom)
            outRect.top = if (_marginTop < 0) _marginTop else 0
            outRect.bottom = if (_marginBottom < 0) _marginBottom else 0
        }
    }
}