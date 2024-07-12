package com.chooongg.formView.style

class FormNoneStyle() : AbstractFormStyle() {

    constructor(block: FormNoneStyle.() -> Unit) : this() {
        block.invoke(this)
    }

    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = false
    override fun isFillHorizontalMargin() = false
    override fun isFillVerticalPadding(): Boolean = false
    override fun isFillHorizontalPadding(): Boolean = false
}