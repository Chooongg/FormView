package com.chooongg.formView.option

data class FormOption(
    private val _name: CharSequence?,
    private val _secondaryName: CharSequence?,
    private val _value: String?,
    private val _isVisible: Boolean
) : IFormOption {
    override fun getOptionName(): CharSequence? = _name
    override fun getOptionSecondaryName(): CharSequence? = _secondaryName
    override fun getOptionValue(): String? = _value
    override fun isVisible(): Boolean = _isVisible
}