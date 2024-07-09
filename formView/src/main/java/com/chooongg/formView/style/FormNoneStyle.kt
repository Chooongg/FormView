package com.chooongg.formView.style

import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.updateLayoutParams
import com.chooongg.formView.config.AbstractFormConfig
import com.chooongg.formView.config.FormNoneConfig
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm

class FormNoneStyle(config: AbstractFormConfig = FormNoneConfig()) : AbstractFormStyle(config) {

    override fun onBindStyle(holder: FormItemViewHolder, item: BaseForm<*>) {
//        holder.itemView.updateLayoutParams<MarginLayoutParams> {
//            topMargin = when (item.boundary.top) {
//                FormBoundary.GLOBAL -> marginInfo.top
//                FormBoundary.MIDDLE -> marginInfo.topMedium
//                else -> 0
//            }
//            bottomMargin = when (item.boundary.bottom) {
//                FormBoundary.GLOBAL -> marginInfo.bottom
//                FormBoundary.MIDDLE -> marginInfo.bottomMedium
//                else -> 0
//            }
//            marginStart = when (item.boundary.start) {
//                FormBoundary.GLOBAL -> 0
//                FormBoundary.MIDDLE -> marginInfo.startMedium
//                else -> 0
//            }
//            marginEnd = when (item.boundary.end) {
//                FormBoundary.GLOBAL -> 0
//                FormBoundary.MIDDLE -> marginInfo.endMedium
//                else -> 0
//            }
//        }
    }
}