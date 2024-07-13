package com.chooongg.formView.data

import com.chooongg.formView.FormColumnBlock
import com.chooongg.formView.itemDelegation.IFormName

interface IFormPart : IFormName {
    var isEnabled: Boolean
    var fixedColumn: Int?
    var columnProvider: FormColumnBlock?
}