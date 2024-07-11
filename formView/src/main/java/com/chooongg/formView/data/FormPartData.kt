package com.chooongg.formView.data

import androidx.annotation.IntRange
import com.chooongg.formView.FormColumnProviderBlock
import com.chooongg.formView.delegation.IFormPart

class FormPartData : FormGroupData(), IFormPart {
    override var isEnabled: Boolean = true

    @IntRange(from = 1, to = 10)
    override var fixedColumn: Int? = null

    override var columnProvider: FormColumnProviderBlock? = null
}