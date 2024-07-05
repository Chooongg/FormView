package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle

abstract class AbstractFormItemProvider : FormTextAppearanceHelper {

    abstract fun onCreateViewHolder(style: AbstractFormStyle, parent: ViewGroup): View

    open fun onViewAttachedToWindow(holder: FormItemViewHolder) = Unit

    abstract fun onBindViewHolder(
        holder: FormItemViewHolder, item: BaseForm<*>, enabled: Boolean
    )

    open fun onViewDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onViewRecycled(holder: FormItemViewHolder) = Unit

}