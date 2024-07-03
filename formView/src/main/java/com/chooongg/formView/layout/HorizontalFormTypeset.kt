package com.chooongg.formView.layout

import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.R
import com.google.android.material.button.MaterialButton

class HorizontalFormTypeset : AbstractFormTypeset() {

    override fun onCreateTypeset(parent: ViewGroup): ViewGroup {
        val layout = LinearLayoutCompat(parent.context).apply {
            id = R.id.formLayoutView
            orientation = LinearLayoutCompat.HORIZONTAL
        }
        layout.addView(MaterialButton(layout.context).apply {
            id = R.id.formNameView
            isClickable = false
            gravity = Gravity.NO_GRAVITY
            background = null
            minWidth = 0
            minHeight = 0
            minimumWidth = 0
            minimumHeight = 0
            includeFontPadding = true
        })
        return layout
    }
}