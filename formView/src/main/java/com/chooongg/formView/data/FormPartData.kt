package com.chooongg.formView.data

import androidx.annotation.IntRange
import com.chooongg.formView.FormColumnProviderBlock
import com.chooongg.formView.FormManager
import com.chooongg.formView.itemDelegation.FormFieldImpl
import com.chooongg.formView.itemDelegation.IFormField

class FormPartData : FormGroupData(), IFormPart, IFormField by FormFieldImpl() {
    override var isEnabled: Boolean = true

    @IntRange(from = 1, to = FormManager.FORM_COLUMN_COUNT.toLong())
    override var fixedColumn: Int? = null

    override var columnProvider: FormColumnProviderBlock? = null
}