package com.chooongg.formView.style

import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.util.TypedValue
import androidx.annotation.ColorRes
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrColor
import com.chooongg.ktx.attrColorStateList
import com.google.android.material.shape.MaterialShapeDrawable

class FormCardStyle() : AbstractFormStyle() {

    @ColorRes
    var backgroundTintResId: Int? = null

    constructor(block: FormCardStyle.() -> Unit) : this() {
        block.invoke(this)
    }

    override fun onBindStyle(holder: FormViewHolder, item: AbstractFormItem<*>) {
        holder.itemView.clipToOutline = true
        val shapeDrawable = MaterialShapeDrawable(getShapeAppearanceModel(holder.itemView, item))
        shapeDrawable.fillColor = if (backgroundTintResId != null) {
            holder.itemView.context.resources.getColorStateList(
                backgroundTintResId!!, holder.itemView.context.theme
            )
        } else ColorStateList.valueOf(
            holder.itemView.attrColor(com.google.android.material.R.attr.colorSurfaceContainerHigh)
        )
        holder.itemView.background = shapeDrawable
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (!super.equals(other)) return false
        if (other !is FormCardStyle) return false
        if (backgroundTintResId != other.backgroundTintResId) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()
}