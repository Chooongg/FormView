package com.chooongg.formView.holder

import android.view.View
import android.widget.TextView
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.typeset.AbstractFormTypeset
import kotlinx.coroutines.Job

open class FormViewHolder(
    val style: AbstractFormStyle, val typeset: AbstractFormTypeset, view: View
) : BaseFormViewHolder(view) {

    var job: Job? = null

    init {
        itemView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        itemView.textDirection = TextView.TEXT_DIRECTION_LOCALE
    }
}