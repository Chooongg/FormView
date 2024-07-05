package com.chooongg.formView.typeset

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle

abstract class AbstractFormTypeset : FormTextAppearanceHelper {

    open fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup? = null

    open fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView)
    }

    open fun onTypesetAttachedToWindow(holder: FormItemViewHolder) = Unit

    open fun onBindTypeset(holder: FormItemViewHolder, item: BaseForm<*>) = Unit

    open fun onBindTypeset(
        holder: FormItemViewHolder, item: BaseForm<*>, payloads: MutableList<Any>
    ) = Unit

    open fun onTypesetDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onTypesetRecycled(holder: FormItemViewHolder) = Unit

}