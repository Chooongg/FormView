package com.chooongg.formView.style

import android.graphics.drawable.RippleDrawable
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.widget.OutlinedCutoutDrawable
import com.chooongg.ktx.attrColorStateList
import com.chooongg.ktx.isLayoutRtl
import com.google.android.material.shape.MaterialShapeDrawable

class FormCardOutlinedStyle() : AbstractFormStyle() {

    constructor(block: FormCardOutlinedStyle.() -> Unit) : this() {
        block.invoke(this)
    }

    @ColorRes
    var strokeColorResId: Int? = null

    @DimenRes
    var strokeWidthResId: Int? = null

    override fun onBindStyle(holder: FormViewHolder, item: AbstractFormItem<*>) {
        holder.itemView.clipToOutline = true
        val shape = getShapeAppearanceModel(holder.itemView, item)
        val shapeDrawable =
            OutlinedCutoutDrawable(shape, item.boundary, holder.itemView.isLayoutRtl)
        val defaultStyle = TypedValue().also {
            holder.itemView.context.theme.resolveAttribute(
                com.google.android.material.R.attr.materialCardViewOutlinedStyle, it, true
            )
        }
        val strokeWidth = if (strokeWidthResId != null) {
            holder.itemView.context.resources.getDimension(strokeWidthResId!!)
        } else with(defaultStyle) {
            holder.itemView.context.obtainStyledAttributes(
                resourceId, intArrayOf(com.google.android.material.R.attr.strokeWidth)
            ).use { it.getDimension(0, 3f) }
        }
        val strokeColor = if (strokeColorResId != null) {
            holder.itemView.context.resources.getColorStateList(
                strokeColorResId!!, holder.itemView.context.theme
            )
        } else with(defaultStyle) {
            holder.itemView.context.obtainStyledAttributes(
                resourceId, intArrayOf(com.google.android.material.R.attr.strokeColor)
            ).use { it.getColorStateList(0) }
        }
        shapeDrawable.setStroke(strokeWidth, strokeColor)
        holder.itemView.background = shapeDrawable
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (!super.equals(other)) return false
        if (other !is FormCardOutlinedStyle) return false
        if (strokeColorResId != other.strokeColorResId) return false
        if (strokeWidthResId != other.strokeWidthResId) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()
}