package com.chooongg.formView

import android.util.TypedValue
import android.widget.TextView
import com.google.android.material.textview.MaterialTextView

object FormUtils {

    fun getFontRealHeight(textView: TextView): Int {
        val tempView = MaterialTextView(textView.context)
        tempView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.textSize)
        tempView.measure(0, 0)
        return tempView.measuredHeight
    }
}