package com.chooongg.formView

import com.chooongg.formView.data.IFormGroupData
import com.chooongg.formView.item.FormDivider
import com.chooongg.formView.item.FormText

fun IFormGroupData.divider(block: (FormDivider.() -> Unit)? = null) =
    addItem(FormDivider().apply { block?.invoke(this) })

fun IFormGroupData.text(
    name: Any?, content: Any? = null, block: (FormText.() -> Unit)? = null
) = addItem(FormText(name, null, content).apply { block?.invoke(this) })