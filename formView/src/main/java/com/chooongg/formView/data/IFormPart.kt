package com.chooongg.formView.data

import com.chooongg.formView.FormColumnBlock
import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.itemDelegation.IFormName

interface IFormPart : IFormName {
    var isEnabled: Boolean
    var column: FormColumn?
}