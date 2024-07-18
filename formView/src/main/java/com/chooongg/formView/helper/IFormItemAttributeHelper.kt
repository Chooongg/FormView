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

    fun obtainNameFormatter(holder: FormViewHolder): FormNameFormatter {
        return holder.style.nameFormatter ?: getFormAdapter(holder)?.data?.dataConfig?.nameFormatter
        ?: getBindingPart(holder)?.data?.partConfig?.nameFormatter
        ?: FormManager.globalConfig.nameFormatter
    }

    fun obtainGroupTitleProvider(holder: FormViewHolder): AbstractGroupTitleProvider {
        return holder.style.groupTitleProvider
            ?: getFormAdapter(holder)?.data?.dataConfig?.groupTitleProvider
            ?: getBindingPart(holder)?.data?.partConfig?.groupTitleProvider
            ?: FormManager.globalConfig.groupTitleProvider
    }

    fun obtainChildTitleProvider(holder: FormViewHolder): AbstractGroupTitleProvider {
        return holder.style.childTitleProvider
            ?: getFormAdapter(holder)?.data?.dataConfig?.childTitleProvider
            ?: getBindingPart(holder)?.data?.partConfig?.childTitleProvider
            ?: FormManager.globalConfig.childTitleProvider
    }

    fun obtainNestedTitleProvider(holder: FormViewHolder): AbstractNestedTitleProvider {
        return holder.style.nestedTitleProvider
            ?: getFormAdapter(holder)?.data?.dataConfig?.nestedTitleProvider ?: getBindingPart(
                holder
            )?.data?.partConfig?.nestedTitleProvider ?: FormManager.globalConfig.nestedTitleProvider
    }

    fun obtainIconGravity(holder: FormViewHolder, item: AbstractFormItem<*>): Int {
        return item.iconGravity ?: holder.style.nameIconGravity
        ?: getFormAdapter(holder)?.data?.dataConfig?.nameIconGravity
        ?: getBindingPart(holder)?.data?.partConfig?.nameIconGravity
        ?: FormManager.globalConfig.nameIconGravity
    }

    fun obtainNameGravity(holder: FormViewHolder, item: AbstractFormItem<*>): Int {
        val nameGravity = item.nameGravity ?: holder.style.nameGravity
        ?: getFormAdapter(holder)?.data?.dataConfig?.nameGravity
        ?: getBindingPart(holder)?.data?.partConfig?.nameGravity
        ?: FormManager.globalConfig.nameGravity
        return nameGravity.obtain(item.columnCount, item.columnSize)
    }

    fun obtainContentGravity(holder: FormViewHolder, item: AbstractFormItem<*>): Int {
        val contentGravity = item.contentGravity ?: holder.style.contentGravity ?: getFormAdapter(
            holder
        )?.data?.dataConfig?.contentGravity
        ?: getBindingPart(holder)?.data?.partConfig?.contentGravity
        ?: FormManager.globalConfig.contentGravity
        return contentGravity.obtain(item.columnCount, item.columnSize)
    }

    fun configMenuView(
        holder: FormViewHolder, item: AbstractFormItem<*>, menuView: FormMenuView
    ) {
        menuView.setMenu(item, getBindingPart(holder)?.isEnabled ?: false) { view, menu ->
            val isIntercept = item.onMenuItemClickListener?.invoke(holder.itemView, view, menu)
            if (isIntercept != true) {
                (holder.bindingAdapter as? AbstractFormPart<*>)?._recyclerView?.onMenuClickListener?.onFormMenuClick(
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