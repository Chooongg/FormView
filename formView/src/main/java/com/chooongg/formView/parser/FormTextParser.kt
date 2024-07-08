package com.chooongg.formView.parser

import android.content.Context

interface FormTextParser {
    fun parse(context: Context, text: Any?): CharSequence?
}