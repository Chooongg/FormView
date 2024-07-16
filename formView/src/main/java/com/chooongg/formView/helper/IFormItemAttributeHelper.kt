package com.chooongg.formView.helper

import com.chooongg.formView.FormAdapter
import com.chooongg.formView.FormManager
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nestedTitle.AbstractNestedTitleProvider
import com.chooongg.formView.widget.FormMenuView

interface IFormItemAttributeHelper {

    fun obtainGroupTitleProvider(holder: FormViewHolder): AbstractGroupTitleProvider {
        return holder.style.groupTitleProvider ?: getFormAdapter(holder)?.data?.groupTitleProvider
        ?: FormManager.globalConfig.groupTitleProvider
    }

    fun obtainChildTitleProvider(holder: FormViewHolder): AbstractGroupTitleProvider {
        return holder.style.childTitleProvider ?: getFormAdapter(holder)?.data?.childTitleProvider
        ?: FormManager.globalConfig.childTitleProvider
    }

    fun obtainNestedTitleProvider(holder: FormViewHolder): AbstractNestedTitleProvider {
        return holder.style.nestedTitleProvider ?: getFormAdapter(holder)?.data?.nestedTitleProvider
        ?: FormManager.globalConfig.nestedTitleProvider
    }

    fun obtainIconGravity(holder: FormViewHolder, item: AbstractFormItem<*>): Int {
        return item.iconGravity ?: holder.style.nameIconGravity
        ?: getFormAdapter(holder)?.data?.nameIconGravity ?: FormManager.globalConfig.nameIconGravity
    }

    fun obtainNameGravity(holder: FormViewHolder, item: AbstractFormItem<*>): Int {
        val nameGravity = item.nameGravity ?: holder.style.nameGravity
        ?: getFormAdapter(holder)?.data?.nameGravity ?: FormManager.globalConfig.nameGravity
        return nameGravity.obtain(item.columnCount, item.columnSize)
    }

    fun obtainNameFormatter(holder: FormViewHolder): FormNameFormatter {
        return holder.style.nameFormatter ?: getFormAdapter(holder)?.data?.nameFormatter
        ?: FormManager.globalConfig.nameFormatter
    }

    fun obtainContentGravity(holder: FormViewHolder, item: AbstractFormItem<*>): Int {
        val contentGravity = item.contentGravity ?: holder.style.contentGravity ?: getFormAdapter(
            holder
        )?.data?.contentGravity ?: FormManager.globalConfig.contentGravity
        return contentGravity.obtain(item.columnCount, item.columnSize)
    }

    fun configMenuView(
        holder: FormViewHolder, item: AbstractFormItem<*>, menuView: FormMenuView
    ) {
        menuView.setMenu(item, item.isEnabled ?: false) { view, menu ->
            val isIntercept = item.onMenuItemClickListener?.invoke(holder.itemView, view, menu)
            if (isIntercept != true) {
                (holder.bindingAdapter as? AbstractFormPart<*>)?._recyclerView?.onMenuClickListener?.invoke(
                    holder.itemView, view, menu, item
                )
            }
        }
    }

    fun getBindingPart(holder: FormViewHolder): AbstractFormPart<*>? {
        return holder.bindingAdapter as? AbstractFormPart<*>
    }

    fun getFormAdapter(holder: FormViewHolder): FormAdapter? {
        return getBindingPart(holder)?._adapter
    }
}