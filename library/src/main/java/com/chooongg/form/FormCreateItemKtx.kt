package com.chooongg.form

import com.chooongg.form.data.IFormGroupData
import com.chooongg.form.item.FormText

//fun IFormGroupData.addButton(
//    name: Any?, field: String? = null, block: (FormButton.() -> Unit)? = null
//) = addItem(FormButton(name, field).apply { block?.invoke(this) })
//
//fun IFormGroupData.addDetail(
//    name: Any?, field: String? = null, block: (FormDetail.() -> Unit)? = null
//) = addItem(FormDetail(name, field).apply { block?.invoke(this) })
//
//fun IFormGroupData.addInput(
//    name: Any?, field: String? = null, block: (FormInput.() -> Unit)? = null
//) = addItem(FormInput(name, field).apply { block?.invoke(this) })

fun IFormGroupData.addText(
    name: Any?, field: String? = null, block: (FormText.() -> Unit)? = null
) = addItem(FormText(name, field).apply { block?.invoke(this) })
