package com.chooongg.formView.itemProvider

import android.animation.AnimatorInflater
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.updateLayoutParams
import com.chooongg.formView.FormManager
import com.chooongg.formView.R
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.FormButton
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.ktx.attrResourcesId
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.theme.overlay.MaterialThemeOverlay

class FormButtonProvider : AbstractFormItemProvider() {

    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View {
        return ConstraintLayout(parent.context).also {
            it.addView(MaterialButton(parent.context).apply {
                id = R.id.formContentView
                layoutParams = ConstraintLayout.LayoutParams(0, -2).apply {
                    matchConstraintMaxWidth = maxWidth
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topMargin = style.padding.topMedium - insetTop
                    bottomMargin = style.padding.bottomMedium - insetBottom
                    marginStart = style.padding.startMedium
                    marginEnd = style.padding.endMedium
                }
            })
        }
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>
    ) {
        with(holder.getView<MaterialButton>(R.id.formContentView)) {
            isEnabled = item.isEnabled
            text = FormManager.parseText(context, item.name)
            configButtonStyle(this, item)
            configGravity(this, item)
        }
    }

    override fun onBindViewHolderClick(
        holder: FormViewHolder, part: AbstractFormPart<*>, item: AbstractFormItem<*>
    ) {
        holder.getView<MaterialButton>(R.id.formContentView).setOnClickListener {
            val listener = item.onClickListener ?: part._recyclerView?.onItemClickListener
            ?: return@setOnClickListener
            listener.onFormItemClick(holder.itemView, part, item)
        }
    }

    private fun configButtonStyle(view: MaterialButton, item: AbstractFormItem<*>) {
        val style = (item as? FormButton)?.buttonStyle ?: view.attrResourcesId(
            com.google.android.material.R.attr.materialButtonStyle,
            com.google.android.material.R.style.Widget_Material3_Button
        )
        val wrap = MaterialThemeOverlay.wrap(view.context, null, 0, style)
        view.setTextColor(wrap.obtainStyledAttributes(
            style, intArrayOf(android.R.attr.textColor)
        ).use { it.getColorStateList(0) })
        view.iconTint = wrap.obtainStyledAttributes(
            style, intArrayOf(androidx.appcompat.R.attr.iconTint)
        ).use { it.getColorStateList(0) }
        view.backgroundTintList = wrap.obtainStyledAttributes(
            style, intArrayOf(androidx.appcompat.R.attr.backgroundTint)
        ).use { it.getColorStateList(0) }
        view.strokeColor = wrap.obtainStyledAttributes(
            style, intArrayOf(com.google.android.material.R.attr.strokeColor)
        ).use { it.getColorStateList(0) }
        view.strokeWidth = wrap.obtainStyledAttributes(
            style, intArrayOf(com.google.android.material.R.attr.strokeWidth)
        ).use { it.getDimensionPixelSize(0, 0) }
        view.rippleColor = wrap.obtainStyledAttributes(
            style, intArrayOf(com.google.android.material.R.attr.rippleColor)
        ).use { it.getColorStateList(0) }
        view.elevation = wrap.obtainStyledAttributes(
            style, intArrayOf(com.google.android.material.R.attr.elevation)
        ).use { it.getDimension(0, 0f) }
        view.shapeAppearanceModel = ShapeAppearanceModel.builder(
            view.context, wrap.obtainStyledAttributes(
                style, intArrayOf(com.google.android.material.R.attr.shapeAppearance)
            ).use {
                it.getResourceId(
                    0, com.google.android.material.R.style.ShapeAppearance_Material3_Corner_Full
                )
            }, 0
        ).build()
        val stateListId = wrap.obtainStyledAttributes(
            style, intArrayOf(android.R.attr.stateListAnimator)
        ).use { it.getResourceId(0, 0) }
        view.stateListAnimator = if (stateListId != 0) {
            AnimatorInflater.loadStateListAnimator(wrap, stateListId)
        } else null
    }

    private fun configGravity(view: MaterialButton, item: AbstractFormItem<*>) {
        val gravity =
            item.contentGravity?.obtain(item.columnCount, item.columnSize) ?: Gravity.NO_GRAVITY
        view.updateLayoutParams<ConstraintLayout.LayoutParams> {
            when {
                gravity == Gravity.TOP -> {
                    width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                }

                gravity == Gravity.BOTTOM -> {
                    width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.UNSET
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                }

                gravity == Gravity.START -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.UNSET
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                }

                gravity == Gravity.END -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.UNSET
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                }

                gravity == Gravity.START or Gravity.TOP -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.UNSET
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                }

                gravity == Gravity.START or Gravity.BOTTOM -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.UNSET
                    topToTop = ConstraintLayout.LayoutParams.UNSET
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                }

                gravity == Gravity.END or Gravity.TOP -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.UNSET
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                }

                gravity == Gravity.END or Gravity.BOTTOM -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.UNSET
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.UNSET
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                }

                gravity and Gravity.CENTER == Gravity.CENTER -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                }

                gravity and Gravity.CENTER_HORIZONTAL == Gravity.CENTER_HORIZONTAL -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    if (gravity and Gravity.TOP == Gravity.TOP) {
                        topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                        bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                    } else if (gravity and Gravity.BOTTOM == Gravity.BOTTOM) {
                        topToTop = ConstraintLayout.LayoutParams.UNSET
                        bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    } else {
                        topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                        bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    }
                }

                gravity and Gravity.CENTER_VERTICAL == Gravity.CENTER_VERTICAL -> {
                    width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    if (gravity and Gravity.START == Gravity.START) {
                        startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                        endToEnd = ConstraintLayout.LayoutParams.UNSET
                    } else if (gravity and Gravity.END == Gravity.END) {
                        startToStart = ConstraintLayout.LayoutParams.UNSET
                        endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    } else {
                        startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                        endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    }
                }

                else -> {
                    width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                }
            }
        }
    }
}