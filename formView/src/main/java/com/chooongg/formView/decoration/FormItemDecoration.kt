package com.chooongg.formView.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chooongg.formView.FormView
import com.chooongg.formView.R
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.ktx.isLayoutRtl
import com.chooongg.ktx.logE
import com.google.android.flexbox.FlexInternalFunction

class FormItemDecoration : ItemDecoration() {

    init {
//        FlexboxItemDecoration()
    }

    private data class Test(val firstIndex: Int, val itemCount: Int)

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        if (parent !is FormView) return
        val holder = parent.getChildViewHolder(view) as? FormItemViewHolder ?: return
        val position = holder.absoluteAdapterPosition
        val adapter = parent.adapter ?: return
        val layoutManager = parent.layoutManager
//        getCacheFromState(state)[position] =
//            FlexInternalFunction.getPositionToFlexLineIndex(layoutManager, position)
        if (!state.didStructureChange() || state.willRunPredictiveAnimations()) {
            val item = adapter.getItem(holder.absoluteAdapterPosition) ?: return
            val line = getFlexLineFromPosition(state, position)
            val start = if (line.first == position) {
                if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
                FormBoundary.GLOBAL
            } else {
                if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
                FormBoundary.NONE
            }
            val end = if (line.second == position) {
                if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
                FormBoundary.GLOBAL
            } else {
                if (parent.isLayoutRtl) outRect.right = 0 else outRect.left = 0
                FormBoundary.NONE
            }
            val top = if (line.first == -1) {
                outRect.top = 0
                FormBoundary.NONE
            } else if (line.first == 0) {
                outRect.top = 0
                FormBoundary.GLOBAL
            } else if (item.positionInGroup == 0 || adapter.getItem(line.first)!!.positionInGroup == 0) {
                outRect.top = holder.style.marginInfo.topMedium
                FormBoundary.MIDDLE
            } else {
                outRect.top = 0
                FormBoundary.NONE
            }
            val bottom = if (line.second == -1) {
                outRect.bottom = 0
                FormBoundary.NONE
            } else if (line.second == state.itemCount - 1) {
                outRect.bottom = 0
                FormBoundary.GLOBAL
            } else if (item.positionInGroup == item.countInGroup - 1) {
                outRect.bottom = holder.style.marginInfo.bottomMedium
                FormBoundary.MIDDLE
            } else if (adapter.getItem(line.second)!!.positionInGroup == adapter.getItem(line.second)!!.countInGroup - 1) {
                outRect.bottom = holder.style.marginInfo.bottomMedium
                FormBoundary.MIDDLE
            } else {
                outRect.bottom = 0
                FormBoundary.NONE
            }
            item.boundary = FormBoundary(start, top, end, bottom)
        }
    }

    private fun getCacheFromState(state: RecyclerView.State): HashMap<Int, Int> {
        var get = state.get<HashMap<Int, Int>>(R.id.formItemDecorationCache)
        if (get == null) {
            get = HashMap()
            state.put(R.id.formItemDecorationCache, get)
        }
        return get
    }

    private fun getFlexLineFromPosition(state: RecyclerView.State, position: Int): Pair<Int, Int> {
        val map = getCacheFromState(state)
        val lineIndex = map[position]
        var first = position
        var last = position
        var findFirst = position - 1
        while (map[findFirst] == lineIndex) {
            first = findFirst
            findFirst--
        }
        var findLast = position + 1
        while (map[findLast] == lineIndex) {
            last = findLast
            findLast++
        }
        return Pair(first, last)
    }
}