package com.chooongg.formView.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chooongg.formView.FormView
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.ktx.isLayoutRtl
import com.chooongg.ktx.logE
import com.google.android.flexbox.FlexInternalFunction
import com.google.android.flexbox.FlexLine
import com.google.android.flexbox.FlexboxItemDecoration

class FormItemDecoration : ItemDecoration() {

    private data class Test(val firstIndex: Int, val itemCount: Int)

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        FlexboxItemDecoration(view.context)
        if (parent !is FormView) return
        val holder = parent.getChildViewHolder(view) as? FormItemViewHolder ?: return
        if (holder.absoluteAdapterPosition == 0) return
        val position = holder.absoluteAdapterPosition
        logE("Form", "POSITION: ${position}, STATE: ${state}")
        val adapter = parent.adapter ?: return
        val layoutManager = parent.layoutManager
        val item = adapter.getItem(holder.absoluteAdapterPosition) ?: return
        val flexLines = layoutManager.flexLines
//        val tests = ArrayList<Test>()
//        flexLines.forEach {
//            tests.add(Test(it.firstIndex, it.itemCount))
//        }
//        logE("Form", "lines: ${tests}")
        val lineIndex = FlexInternalFunction.getPositionToFlexLineIndex(layoutManager, position)
        logE("Form", "lineIndex: ${lineIndex}")
        val line = getPositionToFlexLineIndex(flexLines, position) ?: return
        val start = if (line.firstIndex == position) {
            if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
            FormBoundary.GLOBAL
        } else {
            if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
            FormBoundary.NONE
        }
        val end = if (line.firstIndex + line.itemCount - 1 == position) {
            if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
            FormBoundary.GLOBAL
        } else {
            if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
            FormBoundary.NONE
        }
        val top = if (line.firstIndex == 1) {
            outRect.top = holder.style.marginInfo.top
            FormBoundary.GLOBAL
        } else if (item.positionInGroup == 0 || adapter.getItem(line.firstIndex)?.positionInGroup == 0) {
            outRect.top = holder.style.marginInfo.topMedium
            FormBoundary.MIDDLE
        } else {
            outRect.top = 0
            FormBoundary.NONE
        }
        val bottom = FormBoundary.NONE
        item.boundary = FormBoundary(start, top, end, bottom)
        logE("Form", "boundary: ${item.boundary}")
    }

    private fun getPositionToFlexLineIndex(flexLines: List<FlexLine>, position: Int): FlexLine? {
        return flexLines.find { line ->
            position in line.firstIndex..<line.firstIndex + line.itemCount
        }
    }
}