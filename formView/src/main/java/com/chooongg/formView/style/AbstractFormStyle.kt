package com.chooongg.formView.style

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm

abstract class AbstractFormStyle {

    open fun onCreateStyle(parent: ViewGroup): ViewGroup? = null

    open fun configStyleAddChildView(styleView: ViewGroup, childView: View) {
        styleView.addView(childView)
    }

    open fun onStyleAttachedToWindow(holder: FormItemViewHolder) = Unit

    open fun onBindStyle(holder: FormItemViewHolder, item: BaseForm<*>) = Unit

    open fun onBindStyle(holder: FormItemViewHolder, item: BaseForm<*>, payloads: MutableList<Any>) =
        Unit

    open fun onStyleDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onStyleRecycled(holder: FormItemViewHolder) = Unit
}