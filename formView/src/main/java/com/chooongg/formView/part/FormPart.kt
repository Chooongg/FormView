package com.chooongg.formView.part

import com.chooongg.formView.data.FormPartData
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle

class FormPart(
    style: AbstractFormStyle, data: FormPartData, isEnabled: Boolean
) : AbstractFormPart<FormPartData>(style, data, isEnabled) {

    override fun getOriginalItemList(): List<List<BaseForm<*>>> {
        if (data.partEnabled) {
            val list = ArrayList<BaseForm<*>>()
            val title = data
        }
    }

    override fun executeUpdate(commitCallback: Runnable) {
        val items = ArrayList<BaseForm<*>>()
        data.getItems().forEach {
            it.enabled = it.isEnable(isEnabled)
            it.resetInternalData()
            if (it.isVisible(isEnabled)) {
                when (it) {
                    else -> items.add(it)
                }
            }
        }
        while (items.firstOrNull()?.showAtEdge == false) {
            items.removeFirst()
        }
        while (items.lastOrNull()?.showAtEdge == false) {
            items.removeLast()
        }
        items.forEachIndexed { index, item ->
            item.localPosition = index
            item.groupCount = 1
            item.groupIndex = 0
            item.countInGroup = items.size
            item.positionInGroup = index
        }
        differ.submitList(items, commitCallback)
    }

    override fun get(field: String): BaseForm<*>? = data.getItems().find { it.field == field }
    override fun contains(field: String) = data.getItems().any { it.field == field }
    override fun contains(item: BaseForm<*>) = data.getItems().contains(item)
}