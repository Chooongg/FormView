package com.chooongg.form.extractor

import android.content.Context

interface ITextExtractor {
    fun extract(context: Context, text: Any?): CharSequence?
}