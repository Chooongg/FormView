package com.chooongg.formView.data

import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.itemDelegation.FormFieldImpl
import com.chooongg.formView.itemDelegation.IFormField

class FormPartData : FormGroupData(), IFormPart, IFormField by FormFieldImpl() {
    override var isEnabled: Boolean = true
    override var column: FormColumn? = null
}