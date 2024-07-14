package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.FormLabel
import com.chooongg.formView.item.FormTip
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.button.MaterialButton

class FormTipProvider:AbstractFormItemProvider() {
    override fun onCreateViewHolder(
        part: AbstractFormPart<*>,
        style: AbstractFormStyle,
        parent: ViewGroup
    ): View {
        return MaterialButton(parent.context).apply {
            id = R.id.formNameView
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
        }
    }

    override fun onBindViewHolder(
        holder: FormViewHolder,
        item: AbstractFormItem<*>,
        enabled: Boolean
    ) {
        with(holder.getView<MaterialButton>(R.id.formNameView)) {
            iconGravity = obtainIconGravity(holder, item)
            val nameIcon = FormManager.parseIcon(context, item.icon)
            if (nameIcon != null) {
                icon = nameIcon
                iconTint = item.iconTint?.invoke(context) ?: textColors
            } else icon = null
            gravity = obtainNameGravity(holder, item)
            text = obtainNameFormatter(holder).format(context, item)
            if (item is FormTip && item.textAppearance != null) {
                setTextAppearance(item.textAppearance!!)
            } else setTextAppearance(formTextAppearance(R.attr.formTextAppearanceLabel))
        }
    }
}