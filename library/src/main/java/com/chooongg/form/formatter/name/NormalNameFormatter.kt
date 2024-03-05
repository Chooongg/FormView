package com.chooongg.form.formatter.name

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.res.use
import com.chooongg.form.FormManager
import com.chooongg.form.item.BaseForm
import com.google.android.material.R

class NormalNameFormatter : AbstractNameFormatter() {
    override fun format(context: Context, item: BaseForm<*>): CharSequence? {
        val name = FormManager.extractText(context, item.name)
        if (item.required.not()) return name
        val ssb = SpannableStringBuilder()
        if (name.isNullOrEmpty()) {
            val colorSpan = ForegroundColorSpan(
                context.obtainStyledAttributes(intArrayOf(R.attr.colorError))
                    .use { it.getColor(0, Color.GRAY) }
            )
            return ssb.append("⋆", colorSpan, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return ssb.append(name)
    }
}