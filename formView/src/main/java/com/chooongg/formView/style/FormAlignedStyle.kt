package com.chooongg.formView.style

class FormAlignedStyle() : AbstractFormStyle() {

    constructor(block: FormAlignedStyle.() -> Unit) : this() {
        block.invoke(this)
    }

    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = false
    override fun isFillVerticalPadding(): Boolean = true
}