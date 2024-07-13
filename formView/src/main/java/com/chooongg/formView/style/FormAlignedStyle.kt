package com.chooongg.formView.style

class FormAlignedStyle() : AbstractFormStyle() {

    @Deprecated("Disable")
    override var isIndependentItem: Boolean = false

    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = true
    override fun isFillVerticalPadding(): Boolean = false
}