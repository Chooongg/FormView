package com.chooongg.formView.data

import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.item.FormText

interface IFormGroupData {

    fun getItems(): MutableList<BaseForm<*>>

    fun addItem(item: BaseForm<*>) = getItems().add(item)

    fun text(
        name: Any?,
        field: String? = null,
        content: Any? = null,
        block: (FormText.() -> Unit)? = null
    ) = addItem(FormText(name, field, content).apply { block?.invoke(this) })
}