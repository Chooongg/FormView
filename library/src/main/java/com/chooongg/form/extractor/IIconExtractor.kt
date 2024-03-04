package com.chooongg.form.extractor

import android.content.Context
import android.graphics.drawable.Drawable

interface IIconExtractor {
    fun extract(context: Context, icon: Any?): Drawable?
}