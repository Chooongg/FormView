package com.chooongg.formView.style

import com.chooongg.formView.config.AbstractFormConfig
import com.chooongg.formView.config.FormNoneConfig

class FormAlignedStyle(config: AbstractFormConfig = FormNoneConfig()) : AbstractFormStyle(config) {
    override fun isDecorateNoneItem(): Boolean = false
}