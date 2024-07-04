package com.chooongg.formView.typeset

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.R
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView
import com.google.android.material.button.MaterialButton

class HorizontalFormTypeset : AbstractFormTypeset() {

    override fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup {
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
        }, LinearLayoutCompat.LayoutParams(-2, -2))
        layout.addView(FormMenuView(layout.context, style).apply {
            id = R.id.formMenuView
        }, LinearLayoutCompat.LayoutParams(-2, -2))
        return layout
    }

    override fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView, 1, LinearLayoutCompat.LayoutParams(0, -2).apply {
            weight = 1f
        })
    }
}