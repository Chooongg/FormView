package com.chooongg.formView.typeset

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView
import com.google.android.material.button.MaterialButton

class FormHorizontalTypeset() : AbstractFormTypeset() {

    override var emsMode: FormEmsMode = FormEmsMode.FIXED

    constructor(block: FormHorizontalTypeset.() -> Unit) : this() {
        block.invoke(this)
    }

    override fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup =
        LinearLayoutCompat(parent.context).also {
            it.id = R.id.formTypesetView
            it.orientation = LinearLayoutCompat.HORIZONTAL
            it.isBaselineAligned = false
            it.addView(MaterialButton(it.context).apply {
                id = R.id.formNameView
                setTextAppearance(formTextAppearance(R.attr.formTextAppearanceName))
                insetTop = 0
                insetBottom = 0
                isClickable = false
                background = null
                val fontHeight = FormUtils.getFontRealHeight(this)
                iconSize = FormUtils.getFontRealHeight(this)
                minWidth = 0
                minHeight = fontHeight + style.padding.topMedium + style.padding.bottomMedium
                minimumWidth = 0
                minimumHeight = minHeight
                iconPadding = (style.padding.startMedium + style.padding.endMedium) / 2
                setPaddingRelative(
                    style.padding.startMedium,
                    style.padding.topMedium,
                    style.padding.endMedium,
                    style.padding.bottomMedium
                )
            }, LinearLayoutCompat.LayoutParams(-2, -1))
            it.addView(FormMenuView(it.context, style).apply {
                id = R.id.formMenuView
            }, LinearLayoutCompat.LayoutParams(-2, -2))
        }

    override fun onBindTypeset(holder: FormViewHolder, item: AbstractFormItem<*>) {
        with(holder.getView<MaterialButton>(R.id.formNameView)) {
            iconGravity = obtainIconGravity(holder, item)
            val nameIcon = FormManager.parseIcon(context, item.icon)
            if (nameIcon != null) {
                icon = nameIcon
                iconTint = item.iconTint?.invoke(context) ?: textColors
            } else icon = null
            gravity = obtainNameGravity(holder, item)
            text = obtainNameFormatter(holder).format(context, item)
            configNameView(holder, item, this)
        }
        configMenuView(holder, item, holder.getView(R.id.formMenuView))
    }

    override fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView, 1, LinearLayoutCompat.LayoutParams(0, -2).apply {
            weight = 1f
        })
    }
}