package com.chooongg.formView.style

import com.chooongg.formView.config.AbstractFormStyleConfig
import com.chooongg.formView.config.FormStyleConfig

class FormAlignedStyle(config: AbstractFormStyleConfig = FormStyleConfig()) :
    AbstractFormStyle(config) {
    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = false
    override fun isFillVerticalPadding(): Boolean = true
}