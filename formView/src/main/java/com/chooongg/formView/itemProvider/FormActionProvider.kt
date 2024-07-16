package com.chooongg.formView.itemProvider

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.FormAction
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView
import com.chooongg.ktx.attrColorStateList
import com.chooongg.ktx.dp2px
import com.chooongg.ktx.gone
import com.chooongg.ktx.resDimensionPixelSize
import com.chooongg.ktx.visible
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.textview.MaterialTextView

class FormActionProvider : AbstractFormItemProvider() {
    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View {
        return LinearLayoutCompat(parent.context).also {
            it.id = R.id.formContentView
            it.isBaselineAligned = false
            it.gravity = Gravity.CENTER_VERTICAL
            it.setPaddingRelative(
                style.padding.start - style.padding.startMedium,
                style.padding.top - style.padding.topMedium,
                style.padding.end - style.padding.endMedium,
                style.padding.bottom - style.padding.bottomMedium
            )
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
            }, LinearLayoutCompat.LayoutParams(-2, -2))
            val fontHeight: Int
            it.addView(MaterialTextView(it.context).apply {
                id = R.id.formContentSecondView
                setTextAppearance(formTextAppearance(R.attr.formTextAppearanceContent))
                fontHeight = FormUtils.getFontRealHeight(this)
                setPaddingRelative(
                    style.padding.startMedium,
                    style.padding.topMedium,
                    style.padding.endMedium,
                    style.padding.bottomMedium
                )
            }, LinearLayoutCompat.LayoutParams(0, -2).apply { weight = 1f })
            it.addView(MaterialTextView(it.context).apply {
                id = R.id.formContentThirdView
                gravity = Gravity.CENTER
                setSingleLine()
                setTextAppearance(formTextAppearance(R.attr.formTextAppearanceBadge))
                background = MaterialShapeDrawable(
                    ShapeAppearanceModel.builder(
                        context, R.style.Form_ShapeAppearance_Badge, 0
                    ).build()
                )
                setPadding(dp2px(6f), dp2px(1f), dp2px(6f), dp2px(1f))
            })
            it.addView(FormMenuView(it.context, style).apply {
                id = R.id.formContentFourthView
            }, LinearLayoutCompat.LayoutParams(-2, -2))
            it.addView(AppCompatImageView(it.context).apply {
                id = R.id.formContentFifthView
                setImageResource(R.drawable.ic_form_arrow_end)
            }, LinearLayoutCompat.LayoutParams(fontHeight, fontHeight))
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>, enabled: Boolean
    ) {
        val itemAction = item as? FormAction
        with(holder.getView<MaterialButton>(R.id.formNameView)) {
            iconGravity = obtainIconGravity(holder, item)
            val nameIcon = FormManager.parseIcon(context, item.icon)
            if (nameIcon != null) {
                icon = nameIcon
                iconTint = item.iconTint?.invoke(context) ?: textColors
            } else icon = null
            gravity = obtainNameGravity(holder, item)
            text = obtainNameFormatter(holder).format(context, item)
            if (item.nameTextAppearance != null) {
                setTextAppearance(item.nameTextAppearance!!)
            } else setTextAppearance(formTextAppearance(R.attr.formTextAppearanceName))
        }
        with(holder.getView<MaterialTextView>(R.id.formContentSecondView)) {
            gravity = obtainContentGravity(holder, item)
            text = FormManager.parseText(context, item.getContentText())
        }
        with(holder.getView<MaterialTextView>(R.id.formContentThirdView)) {
            val shapeBackground = background as MaterialShapeDrawable
            if (itemAction?.badge != null) {
                shapeBackground.fillColor = if (itemAction.badgeColor != null) {
                    itemAction.badgeColor!!.invoke(context)
                } else attrColorStateList(com.google.android.material.R.attr.colorError)
                setTextColor(
                    if (itemAction.badgeTextColor != null) {
                        itemAction.badgeTextColor!!.invoke(context)
                    } else attrColorStateList(com.google.android.material.R.attr.colorOnError)
                )
                val number = itemAction.badge?.toString()?.toIntOrNull()
                if (number != null) {
                    maxWidth = Int.MAX_VALUE
                    if (number == -1) {
                        val size = resDimensionPixelSize(R.dimen.formBadgeNoneSize)
                        layoutParams = LinearLayoutCompat.LayoutParams(size, size)
                        text = null
                    } else {
                        layoutParams = LinearLayoutCompat.LayoutParams(-2, -2)
                        text =
                            if (itemAction.badgeMaxNumber != -2 && number > itemAction.badgeMaxNumber) {
                                "${itemAction.badgeMaxNumber}+"
                            } else "$number"
                    }
                } else {
                    layoutParams = LinearLayoutCompat.LayoutParams(-2, -2)
                    if (itemAction.badgeMaxCharacterCount != -2) {
                        maxEms = itemAction.badgeMaxCharacterCount
                    } else {
                        maxWidth = Int.MAX_VALUE
                    }
                    text = FormManager.parseText(context, itemAction.badge!!)
                }
                visible()
            } else gone()
        }
        configMenuView(holder, item, holder.getView(R.id.formContentFourthView))
        with(holder.getView<AppCompatImageView>(R.id.formContentFifthView)) {
            visibility = if (itemAction?.showNextLevelIcon != false) View.VISIBLE else View.GONE
            val icon = FormManager.parseIcon(context, itemAction?.nextLevelIcon)
            if (icon != null) setImageDrawable(icon) else setImageResource(R.drawable.ic_form_arrow_end)
        }
    }
}