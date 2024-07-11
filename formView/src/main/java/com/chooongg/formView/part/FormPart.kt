package com.chooongg.formView.part

import com.chooongg.formView.data.FormPartData
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle

class FormPart(
    style: AbstractFormStyle, data: FormPartData, isEnabled: Boolean
) : AbstractFormPart<FormPartData>(style, data, isEnabled) {

    override fun getOriginalItemList(): List<List<BaseForm<*>>> = if (data.isEnabled) {
        val itemList = ArrayList<BaseForm<*>>()
        if (!style.config.isIndependentItem) {
            val title = data.getGroupTitleItem()
            if (title != null) itemList.add(title)
        }
        itemList.addAll(data.getItems())
        listOf(itemList)
    } else emptyList()

    override fun get(field: String): BaseForm<*>? = data.getItems().find { it.field == field }
    override fun contains(field: String) = data.getItems().any { it.field == field }
    override fun contains(item: BaseForm<*>) = data.getItems().contains(item)
}