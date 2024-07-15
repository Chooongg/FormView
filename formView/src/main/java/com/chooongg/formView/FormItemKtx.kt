package com.chooongg.formView

import com.chooongg.formView.data.IFormGroupData
import com.chooongg.formView.item.FormAction
import com.chooongg.formView.item.FormDivider
import com.chooongg.formView.item.FormLabel
import com.chooongg.formView.item.FormText
import com.chooongg.formView.item.FormTip

fun IFormGroupData.action(
    name: Any?, field: String? = null, content: Any? = null, block: (FormAction.() -> Unit)? = null
) {
    addItem(FormAction(null, null, null).apply { block?.invoke(this) })
}

fun IFormGroupData.divider(block: (FormDivider.() -> Unit)? = null) {
    addItem(FormDivider().apply { block?.invoke(this) })
}

fun IFormGroupData.label(name: Any?, block: (FormLabel.() -> Unit)? = null) {
    addItem(FormLabel(name).apply { block?.invoke(this) })
}

fun IFormGroupData.text(name: Any?, content: Any? = null, block: (FormText.() -> Unit)? = null) {
    addItem(FormText(name, null, content).apply { block?.invoke(this) })
}

fun IFormGroupData.tip(name: Any?, block: (FormTip.() -> Unit)? = null) {
    addItem(FormTip(name).apply { block?.invoke(this) })
}