package com.chooongg.formView.typeset

import android.view.ViewGroup
import android.widget.FrameLayout
import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.style.AbstractFormStyle

class FormNoneTypeset : AbstractFormTypeset() {
    override var emsMode: FormEmsMode = FormEmsMode(FormEmsMode.NONE)
    override fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup =
        FrameLayout(parent.context)
}