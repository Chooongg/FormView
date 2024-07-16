package com.chooongg.formView.provider.groupTitle

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.button.MaterialButton

class FormNormalChildTitleProvider : AbstractGroupTitleProvider() {
    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View = LinearLayoutCompat(parent.context).also {
        it.orientation = LinearLayoutCompat.HORIZONTAL
        it.addView(MaterialButton(it.context).apply {
            id = R.id.formNameView
            setTextAppearance(formTextAppearance(R.attr.formTextAppearanceChildTitle))
            insetTop = 0
            insetBottom = 0
            isClickable = false
            gravity = Gravity.NO_GRAVITY
            background = null
            minWidth = 0
            minHeight = 0
            minimumWidth = 0
            minimumHeight = 0
            iconSize = FormUtils.getFontRealHeight(this)
            iconPadding = (style.padding.startMedium + style.padding.endMedium) / 2
            setPaddingRelative(
                style.padding.startMedium,
                style.padding.topMedium,
                style.padding.endMedium,
                style.padding.bottomMedium
            )
        }, LinearLayoutCompat.LayoutParams(-1, -2))
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>
    ) {
        with(holder.getView<LinearLayoutCompat>(R.id.formContentView)) {
            setPaddingRelative(
                when (item.boundary.start) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.padding.start - holder.style.padding.startMedium
                }, when (item.boundary.top) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.padding.top - holder.style.padding.topMedium
                }, when (item.boundary.end) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.padding.end - holder.style.padding.endMedium
                }, when (item.boundary.bottom) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.padding.bottom - holder.style.padding.bottomMedium
                }
            )
        }
        with(holder.getView<MaterialButton>(R.id.formNameView)) {
            iconGravity = obtainIconGravity(holder, item)
            val nameIcon = FormManager.parseIcon(context, item.icon)
            if (nameIcon != null) {
                icon = nameIcon
                iconTint = item.iconTint?.invoke(context) ?: textColors
            } else icon = null
            text = obtainNameFormatter(holder).format(context, item)
        }
    }
}