package com.chooongg.formView.holder

import android.view.View
import com.chooongg.formView.layout.AbstractFormTypeset
import com.chooongg.formView.style.AbstractFormStyle

open class FormItemViewHolder(
    val style: AbstractFormStyle, val layout: AbstractFormTypeset, view: View
) : FormViewHolder(view)