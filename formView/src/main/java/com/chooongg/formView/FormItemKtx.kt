package com.chooongg.formView

import com.chooongg.formView.data.IFormGroupData
import com.chooongg.formView.item.FormText

fun IFormGroupData.text(
    name: Any?, field: String? = null, content: Any? = null, block: (FormText.() -> Unit)? = null
) = addItem(FormText(name, field, content).apply { block?.invoke(this) })