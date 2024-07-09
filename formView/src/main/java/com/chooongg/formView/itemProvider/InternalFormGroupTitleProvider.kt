package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle

class InternalFormGroupTitleProvider : AbstractFormItemProvider() {

    override fun onCreateViewHolder(style: AbstractFormStyle, parent: ViewGroup): View =
        style.config.groupTitleProvider.onCreateViewHolder(style, parent)

    override fun onViewAttachedToWindow(holder: FormItemViewHolder) {
        holder.style.config.groupTitleProvider.onViewAttachedToWindow(holder)
    }

    override fun onBindViewHolder(holder: FormItemViewHolder, item: BaseForm<*>, enabled: Boolean) {
        holder.style.config.groupTitleProvider.onBindViewHolder(holder, item, enabled)
    }

    override fun onViewDetachedFromWindow(holder: FormItemViewHolder) {
        holder.style.config.groupTitleProvider.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: FormItemViewHolder) {
        holder.style.config.groupTitleProvider.onViewRecycled(holder)
    }
}