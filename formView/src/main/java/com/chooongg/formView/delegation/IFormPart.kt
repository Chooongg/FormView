package com.chooongg.formView.delegation

import com.chooongg.formView.FormColumnProviderBlock

interface IFormPart {
    var isEnabled: Boolean
    var fixedColumn: Int?
    var columnProvider: FormColumnProviderBlock?
}