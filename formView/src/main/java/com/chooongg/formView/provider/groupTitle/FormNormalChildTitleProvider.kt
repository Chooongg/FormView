package com.chooongg.formView.provider.groupTitle

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.button.MaterialButton

class FormNormalChildTitleProvider : AbstractGroupTitleProvider() {
    override fun onCreateViewHolder(style: AbstractFormStyle, parent: ViewGroup): View =
        LinearLayoutCompat(parent.context).also {
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
                iconPadding = (style.paddingInfo.startMedium + style.paddingInfo.endMedium) / 2
                setPaddingRelative(
                    style.paddingInfo.startMedium,
                    style.paddingInfo.topMedium,
                    style.paddingInfo.endMedium,
                    style.paddingInfo.bottomMedium
                )
            }, LinearLayoutCompat.LayoutParams(-1, -2))
        }

    override fun onBindViewHolder(holder: FormItemViewHolder, item: BaseForm<*>, enabled: Boolean) {
        with(holder.getView<LinearLayoutCompat>(R.id.formContentView)) {
            setPaddingRelative(
                when (item.boundary.start) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.paddingInfo.start - holder.style.paddingInfo.startMedium
                }, when (item.boundary.top) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.paddingInfo.top - holder.style.paddingInfo.topMedium
                }, when (item.boundary.end) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.paddingInfo.end - holder.style.paddingInfo.endMedium
                }, when (item.boundary.bottom) {
                    FormBoundary.NONE -> 0
                    else -> holder.style.paddingInfo.bottom - holder.style.paddingInfo.bottomMedium
                }
            )
        }
        with(holder.getView<MaterialButton>(R.id.formNameView)) {
            iconGravity = item.iconGravity ?: holder.style.config.nameIconGravity
            val nameIcon = FormManager.parseIcon(context, item.icon)
            if (nameIcon != null) {
                icon = nameIcon
                iconTint = item.iconTint?.invoke(context) ?: textColors
            } else icon = null
            text = holder.style.config.nameFormatter.format(context, item)
        }
    }
}