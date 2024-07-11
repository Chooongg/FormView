package com.chooongg.formView.typeset

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.R
import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView

class FormNoneTypeset : AbstractFormTypeset() {

    override var emsMode: FormEmsMode = FormEmsMode(FormEmsMode.NONE)

    override fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup =
        LinearLayoutCompat(parent.context).also {
            it.addView(FormMenuView(it.context, style).apply {
                id = R.id.formMenuView
            }, LinearLayoutCompat.LayoutParams(-2, -2))
        }

    override fun onBindTypeset(holder: FormItemViewHolder, item: AbstractFormItem<*>) {
        configMenuView(holder, item, holder.getView(R.id.formMenuView))
    }

    override fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView, 0, LinearLayoutCompat.LayoutParams(0, -1).apply {
            weight = 1f
        })
    }
}