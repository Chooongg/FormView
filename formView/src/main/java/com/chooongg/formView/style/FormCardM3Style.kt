package com.chooongg.formView.style

import android.content.res.ColorStateList
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrColor
import com.google.android.material.shape.MaterialShapeDrawable

class FormCardM3Style() : AbstractShapeAppearanceFormStyle() {

    constructor(block: FormCardM3Style.() -> Unit) : this() {
        block.invoke(this)
    }

    override fun onBindStyle(holder: FormViewHolder, item: AbstractFormItem<*>) {
        holder.itemView.clipToOutline = true
        val shapeDrawable = MaterialShapeDrawable(getShapeAppearanceModel(holder.itemView, item))
        shapeDrawable.fillColor = ColorStateList.valueOf(
            holder.itemView.attrColor(com.google.android.material.R.attr.colorSurfaceContainerLow)
        )
        holder.itemView.background = shapeDrawable
    }
}