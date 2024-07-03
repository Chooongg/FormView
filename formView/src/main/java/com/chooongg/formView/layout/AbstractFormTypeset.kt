package com.chooongg.formView.layout

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm

abstract class AbstractFormTypeset {

    open fun onCreateTypeset(parent: ViewGroup): ViewGroup? = null

    open fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView)
    }

    open fun onTypesetAttachedToWindow(holder: FormItemViewHolder) = Unit

    open fun onBindTypeset(holder: FormItemViewHolder, item: BaseForm<*>) = Unit

    open fun onBindTypeset(holder: FormItemViewHolder, item: BaseForm<*>, payloads: MutableList<Any>) =
        Unit

    open fun onTypesetDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onTypesetRecycled(holder: FormItemViewHolder) = Unit

}