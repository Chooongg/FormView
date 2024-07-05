package com.chooongg.formView.holder

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.typeset.AbstractFormTypeset

open class FormItemViewHolder(
    val style: AbstractFormStyle,
    val typeset: AbstractFormTypeset,
    view: View
) : FormViewHolder(view) {
    init {
        itemView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        itemView.textDirection = TextView.TEXT_DIRECTION_LOCALE
    }

    fun getTypesetGravity() {
        typeset
    }
}