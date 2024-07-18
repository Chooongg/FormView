package com.chooongg.formView.config

import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.style.AbstractFormStyle

interface IFormPartConfig : IFormStyleConfig {
    var column: FormColumn?
}