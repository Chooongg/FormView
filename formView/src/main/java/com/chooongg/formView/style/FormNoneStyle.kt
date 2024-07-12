package com.chooongg.formView.style

import com.chooongg.formView.config.AbstractFormStyleConfig
import com.chooongg.formView.config.FormStyleConfig

class FormNoneStyle(config: AbstractFormStyleConfig = FormStyleConfig()) :
    AbstractFormStyle(config) {
    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = false
    override fun isFillHorizontalMargin() = false
    override fun isFillVerticalPadding(): Boolean = false
    override fun isFillHorizontalPadding(): Boolean = false
}