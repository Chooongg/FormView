package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.FormManager
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle

class InternalFormGroupTitleProvider : AbstractFormItemProvider() {

    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View {
        val provider = (style.groupTitleProvider ?: part._adapter?.data?.groupTitleProvider
        ?: FormManager.globalConfig.groupTitleProvider)
        return provider.onCreateViewHolder(part, style, parent)
    }


    override fun onViewAttachedToWindow(holder: FormViewHolder) {
        obtainGroupTitleProvider(holder).onViewAttachedToWindow(holder)
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>, enabled: Boolean
    ) {
        obtainGroupTitleProvider(holder).onBindViewHolder(holder, item, enabled)
    }

    override fun onViewDetachedFromWindow(holder: FormViewHolder) {
        obtainGroupTitleProvider(holder).onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: FormViewHolder) {
        obtainGroupTitleProvider(holder).onViewRecycled(holder)
    }
}