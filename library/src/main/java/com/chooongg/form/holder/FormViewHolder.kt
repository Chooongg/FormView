package com.chooongg.form.holder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.provider.AbstractFormProvider
import com.chooongg.form.style.AbstractStyle
import com.chooongg.form.typeset.AbstractTypeset
import kotlinx.coroutines.Job

class FormViewHolder(
    val style: AbstractStyle,
    val styleLayout: ViewGroup?,
    val typeset: AbstractTypeset,
    val typesetLayout: ViewGroup?,
    val provider: AbstractFormProvider,
    val view: View
) : RecyclerView.ViewHolder(styleLayout ?: typesetLayout ?: view) {

    var job: Job? = null

    init {
        if (styleLayout != null && typesetLayout != null) {
            style.executeAddView(styleLayout, typesetLayout)
        }
        if (styleLayout != null && typesetLayout == null) {
            style.executeAddView(styleLayout, view)
        }
        if (typesetLayout != null) {
            typeset.executeAddView(style, typesetLayout, view)
        }
    }
}