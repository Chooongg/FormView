package com.chooongg.form.data

class FormPartData : FormGroupData(), IFormPart {
    override var partEnabled: Boolean = true
    override var partField: String? = null
    override var partName: Any? = null
}
