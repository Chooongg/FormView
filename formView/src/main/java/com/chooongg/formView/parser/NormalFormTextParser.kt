package com.chooongg.formView.parser

import android.content.Context

class NormalFormTextParser : FormTextParser {
    override fun parse(context: Context, text: Any?): CharSequence? = when (text) {
        null -> null
        is Int -> context.getString(text)
        is CharSequence -> text
        else-> text.toString()
    }
}