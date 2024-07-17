package com.chooongg.formView.data

import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.itemDelegation.FormFieldImpl
import com.chooongg.formView.itemDelegation.IFormField

class FormPartData : FormGroupData(), IFormPart, IFormField by FormFieldImpl() {
    override var visibilityMode: FormVisibilityMode = FormVisibilityMode.ALWAYS
    override var column: FormColumn? = null
}