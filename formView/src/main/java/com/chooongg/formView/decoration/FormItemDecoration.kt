package com.chooongg.formView.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chooongg.formView.FormView
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormItemViewHolder
import com.google.android.flexbox.FlexLine
import com.google.android.flexbox.FlexboxItemDecoration

class FormItemDecoration : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        FlexboxItemDecoration(view.context)
        if (parent !is FormView) return
        val holder = parent.getChildViewHolder(view) as? FormItemViewHolder ?: return
        if (holder.absoluteAdapterPosition == 0) return
        val position = holder.absoluteAdapterPosition
        val adapter = parent.adapter ?: return
        val layoutManager = parent.layoutManager
        val item = adapter.getItem(holder.absoluteAdapterPosition) ?: return
        val lineIndex = getPositionToFlexLineIndex(layoutManager.flexLinesInternal, position)
        val line = layoutManager.flexLinesInternal[lineIndex]
        val start = if (line.firstIndex == position) {
            FormBoundary.GLOBAL
        }
    }

    private fun getPositionToFlexLineIndex(flexLines: List<FlexLine>, position: Int): Int {
        flexLines.forEachIndexed { index, line ->
            if (position >= line.firstIndex && position in (line.firstIndex..<line.firstIndex + line.itemCount)) {
                return index
            }
        }
        return -1
    }
}