package com.chooongg.formView

import android.util.TypedValue
import android.widget.TextView

object FormUtils {

    fun getFontRealHeight(textView: TextView): Int {
        val tempView = TextView(textView.context)
        tempView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.textSize)
        tempView.measure(0, 0)
        return tempView.measuredHeight
    }
}