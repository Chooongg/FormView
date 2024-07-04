package com.chooongg.formView.data

class FormPartData : FormGroupData(), IFormPart {
    override var partEnabled: Boolean = true
    override var partField: String? = null
    override var partName: Any? = null
}
