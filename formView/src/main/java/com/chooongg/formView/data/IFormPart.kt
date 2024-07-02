package com.chooongg.formView.data

import com.chooongg.formView.data.IFormIcon

interface IFormPart : IFormIcon {
    var partEnabled: Boolean
    var partField: String?
    var partName: Any?
}