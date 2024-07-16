package com.chooongg.formView.style

import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrColorStateList
import com.google.android.material.shape.MaterialShapeDrawable

class FormCardElevatedStyle() : AbstractFormStyle() {

    @DimenRes
    var elevationResId: Int? = null

    @ColorRes
    var backgroundTintResId: Int? = null

    constructor(block: FormCardElevatedStyle.() -> Unit) : this() {
        block.invoke(this)
    }

    @SuppressLint("PrivateResource")
    override fun onBindStyle(holder: FormViewHolder, item: AbstractFormItem<*>) {
        holder.itemView.clipToOutline = true
        val shapeDrawable = MaterialShapeDrawable(getShapeAppearanceModel(holder.itemView, item))
        shapeDrawable.fillColor = if (backgroundTintResId != null) {
            holder.itemView.context.resources.getColorStateList(
                backgroundTintResId!!, holder.itemView.context.theme
            )
        } else {
            holder.itemView.attrColorStateList(com.google.android.material.R.attr.colorSurfaceContainerLowest)
        }
        holder.itemView.background = shapeDrawable
        holder.itemView.elevation = if (elevationResId != null) {
            holder.itemView.context.resources.getDimension(elevationResId!!)
        } else holder.itemView.context.resources.getDimension(com.google.android.material.R.dimen.m3_card_elevated_elevation)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (!super.equals(other)) return false
        if (other !is FormCardElevatedStyle) return false
        if (elevationResId != other.elevationResId) return false
        if (backgroundTintResId != other.backgroundTintResId) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()
}