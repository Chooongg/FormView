package com.chooongg.formView.formatter.name

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.chooongg.formView.FormManager
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrColor

class NormalFormNameFormatter : FormNameFormatter() {
    override fun format(context: Context, item: AbstractFormItem<*>): CharSequence? {
        val name = FormManager.parseText(context, item.name)
        if (!item.required) return name
        val ssb = SpannableStringBuilder()
        ssb.append(
            "⋆",
            ForegroundColorSpan(context.attrColor(androidx.appcompat.R.attr.colorError)),
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (!name.isNullOrEmpty()) {
            ssb.append(name)
        }
        return ssb
    }
}