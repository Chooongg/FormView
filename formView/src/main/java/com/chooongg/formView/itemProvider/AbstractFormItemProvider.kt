package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle

/**
 * Item Provider
 */
abstract class AbstractFormItemProvider : FormTextAppearanceHelper {

    abstract fun onCreateViewHolder(style: AbstractFormStyle, parent: ViewGroup): View

    open fun onViewAttachedToWindow(holder: FormItemViewHolder) = Unit

    abstract fun onBindViewHolder(
        holder: FormItemViewHolder, item: AbstractFormItem<*>, enabled: Boolean
    )

    open fun onBindViewHolderOther(
        holder: FormItemViewHolder, item: AbstractFormItem<*>, enabled: Boolean, payload: Any
    ) = Unit

    open fun onViewDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onViewRecycled(holder: FormItemViewHolder) = Unit

    protected fun obtainContentGravity(holder: FormItemViewHolder, item: AbstractFormItem<*>): Int {
        val columnCount =
            (holder.bindingAdapter as? AbstractFormPart<*>)?._adapter?.columnCount ?: 1
        return if (columnCount > 1) {
            item.contentGravity?.multiColumnGravity
            ?: holder.style.config.contentGravity.multiColumnGravity
        } else {
            item.contentGravity?.gravity
            ?: holder.style.config.contentGravity.gravity
        }
    }

    override fun equals(other: Any?) = other?.javaClass == javaClass

    override fun hashCode(): Int = javaClass.hashCode()
}