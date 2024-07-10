package com.chooongg.formView.data

import com.chooongg.formView.FormColumnProviderBlock

interface IFormPart {
    var isEnabled: Boolean
    var fixedColumn: Int?
    var columnProvider: FormColumnProviderBlock?
}