package com.chooongg.form

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chooongg.android.ktx.isLayoutRtl
import com.chooongg.android.ktx.logE
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.layoutManager.FormLayoutManager
import com.google.android.flexbox.FlexboxItemDecoration

class FormItemDecoration internal constructor() : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        FlexboxItemDecoration(parent.context)
        val adapter = parent.adapter as? FormAdapter ?: return
        val layoutManager = parent.layoutManager as? FormLayoutManager ?: return
        val holder = parent.getChildViewHolder(view) as? FormViewHolder ?: return
        val item = adapter.getFormItem(holder.absoluteAdapterPosition) ?: return
        val lines = layoutManager.flexLines
        val pair = adapter.getWrappedAdapterAndPosition(holder.absoluteAdapterPosition)
        val line = lines.find { line ->
//            logE(
//                "Form", "${holder.absoluteAdapterPosition}  ${line.firstIndex}   ${line.itemCount}"
//            )
            holder.absoluteAdapterPosition in line.firstIndex .. line.firstIndex + line.itemCount
        } ?: return
        val isTop = if (pair.second == 0) {
            true
        } else {
            var temp = false
            if (line.firstIndex == holder.absoluteAdapterPosition - pair.second) {
                temp = true
            }
            temp
        }
        val isBottom = if (pair.second == pair.first.itemCount - 1) {
            true
        } else {
            var temp = false
            if (line.firstIndex + line.itemCount - 1 == holder.absoluteAdapterPosition + pair.first.itemCount - pair.second - 1) {
                temp = true
            }
            temp
        }
        val isLeft = if (view.isLayoutRtl) {
            line.firstIndex + line.itemCount - 1 == holder.absoluteAdapterPosition
        } else line.firstIndex == holder.absoluteAdapterPosition
        val isRight = if (view.isLayoutRtl) {
            line.firstIndex == holder.absoluteAdapterPosition
        } else line.firstIndex + line.itemCount - 1 == holder.absoluteAdapterPosition
        outRect.top = if (isTop) 0 else 24
        outRect.bottom = if (isBottom) 0 else 24
        outRect.left = if (isLeft) 0 else 24
        outRect.right = if (isRight) 0 else 24
//        logE("Form", "${holder.absoluteAdapterPosition}   ${outRect}")
    }
}