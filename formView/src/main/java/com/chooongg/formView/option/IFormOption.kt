package com.chooongg.formView.option

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan

interface IFormOption {
    fun getOptionName(): CharSequence?
    fun getOptionSecondaryName(): CharSequence? = null
    fun getOptionValue(): String? = null
    fun isVisible(): Boolean = true

    fun getShowText(context: Context): CharSequence? {
        val optionName = getOptionName()
        val optionSecondaryName = getOptionSecondaryName() ?: return getOptionName()
        if (optionName.isNullOrEmpty()) {
            return SpannableString(optionSecondaryName).apply {
                setSpan(RelativeSizeSpan(0.8f), 0, length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        return SpannableStringBuilder(optionName).append(" ").append(
            optionSecondaryName, RelativeSizeSpan(0.8f), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}