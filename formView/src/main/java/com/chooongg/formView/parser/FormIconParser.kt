package com.chooongg.formView.parser

import android.content.Context
import android.graphics.drawable.Drawable

interface FormIconParser {
    fun parse(context: Context, icon: Any?): Drawable?
}