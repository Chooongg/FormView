package com.chooongg.form.part

import com.chooongg.form.data.FormPartData
import com.chooongg.form.item.BaseForm
import com.chooongg.form.style.AbstractStyle

class FormPart(
    style: AbstractStyle, data: FormPartData
) : AbstractPart<FormPartData>(style, data) {

    override fun executeUpdate(commitCallback: Runnable) {
        val items = ArrayList<BaseForm<*>>()
        data.getItems().forEach { item ->
            item.enabled = item.isEnable(adapter.isEnabled)
            item.resetInternalData()
            item.initialize()
            if (item.isVisible(adapter.isEnabled)) {
                when (item) {
                    else -> items.add(item)
                }
            }
        }
        while (items.firstOrNull()?.showAtEdge == false) {
            items.removeFirst()
        }
        while (items.lastOrNull()?.showAtEdge == false) {
            items.removeLast()
        }
        asyncDiffer.submitList(items, commitCallback)
    }

    override fun get(field: String): BaseForm<*>? = data.getItems().find { it.field == field }

    override fun contains(field: String): Boolean = data.getItems().any { it.field == field }

    override fun contains(item: BaseForm<*>): Boolean = data.getItems().contains(item)
}