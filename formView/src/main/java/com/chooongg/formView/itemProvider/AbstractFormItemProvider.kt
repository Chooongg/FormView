package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.config.IFormItemObtainAttr
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle

/**
 * Item Provider
 */
abstract class AbstractFormItemProvider : FormTextAppearanceHelper, IFormItemObtainAttr {

    abstract fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View

    open fun onViewAttachedToWindow(holder: FormViewHolder) = Unit

    abstract fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>, enabled: Boolean
    )

    open fun onBindViewHolderOther(
        holder: FormViewHolder, item: AbstractFormItem<*>, enabled: Boolean, payload: Any
    ) = Unit

    open fun onViewDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onViewRecycled(holder: FormViewHolder) = Unit

    override fun equals(other: Any?) = other?.javaClass == javaClass

    override fun hashCode(): Int = javaClass.hashCode()
}