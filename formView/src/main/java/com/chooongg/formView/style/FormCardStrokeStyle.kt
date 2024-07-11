package com.chooongg.formView.style

import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.chooongg.formView.config.AbstractFormStyleConfig
import com.chooongg.formView.config.FormStyleConfig
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.widget.OutlinedCutoutDrawable
import com.chooongg.ktx.isLayoutRtl

class FormCardStrokeStyle(config: AbstractFormStyleConfig = FormStyleConfig()) :
    AbstractShapeAppearanceFormStyle(config) {

    @ColorRes
    var strokeColorResId: Int? = null

    @DimenRes
    var strokeWidthResId: Int? = null

    override fun onBindStyle(holder: FormItemViewHolder, item: AbstractFormItem<*>) {
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
            holder.itemView.context.resources.getColor(
                strokeColorResId!!, holder.itemView.context.theme
            )
        } else with(defaultStyle) {
            holder.itemView.context.obtainStyledAttributes(
                resourceId, intArrayOf(com.google.android.material.R.attr.strokeColor)
            ).use { it.getColor(0, Color.GRAY) }
        }
        shapeDrawable.setStroke(strokeWidth, strokeColor)
        holder.itemView.background = shapeDrawable
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (!super.equals(other)) return false
        if (other !is FormCardStrokeStyle) return false
        if (strokeColorResId != other.strokeColorResId) return false
        if (strokeWidthResId != other.strokeWidthResId) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()
}