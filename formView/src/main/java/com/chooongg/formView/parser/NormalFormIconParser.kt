package com.chooongg.formView.parser

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

class NormalFormIconParser : FormIconParser {
    override fun parse(context: Context, icon: Any?): Drawable? = when (icon) {
        null -> null
        is Drawable -> icon
        is Int -> ResourcesCompat.getDrawable(context.resources, icon, context.theme)
        else -> null
    }
}