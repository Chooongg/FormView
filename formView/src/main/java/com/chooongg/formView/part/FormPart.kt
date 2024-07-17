package com.chooongg.formView.part

import com.chooongg.formView.data.FormPartData
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.style.AbstractFormStyle

class FormPart(
    style: AbstractFormStyle, data: FormPartData, isEnabled: Boolean
) : AbstractFormPart<FormPartData>(style, data, isEnabled) {

    override fun getOriginalItemList(): List<List<AbstractFormItem<*>>> =
        if (data.isVisible(isEnabled)) {
            val itemList = ArrayList<AbstractFormItem<*>>()
            if (!style.isIndependentItem) {
                val title = data.getGroupTitleItem()
                if (title != null) itemList.add(title)
            }
            itemList.addAll(data.getItems())
            listOf(itemList)
        } else emptyList()

    override fun get(field: String): AbstractFormItem<*>? =
        data.getItems().find { it.field == field }

    override fun contains(field: String) = data.getItems().any { it.field == field }
    override fun contains(item: AbstractFormItem<*>) = data.getItems().contains(item)
}