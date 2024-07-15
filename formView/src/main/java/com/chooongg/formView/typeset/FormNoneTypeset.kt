package com.chooongg.formView.typeset

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.R
import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView

class FormNoneTypeset() : AbstractFormTypeset() {

    override var emsMode: FormEmsMode = FormEmsMode.NONE

    constructor(block: FormNoneTypeset.() -> Unit) : this() {
        block.invoke(this)
    }

    override fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup =
        LinearLayoutCompat(parent.context).also {
            it.addView(FormMenuView(it.context, style).apply {
                id = R.id.formMenuView
            }, FrameLayout.LayoutParams(-2, -2).apply { gravity = Gravity.END })
        }

    override fun onBindTypeset(holder: FormViewHolder, item: AbstractFormItem<*>) {
        configMenuView(holder, item, holder.getView(R.id.formMenuView))
    }

    override fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView, 0, LinearLayoutCompat.LayoutParams(0, -1).apply {
            weight = 1f
        })
    }
}