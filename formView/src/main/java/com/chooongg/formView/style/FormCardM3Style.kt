package com.chooongg.formView.style

import android.content.res.ColorStateList
import com.chooongg.formView.config.AbstractFormConfig
import com.chooongg.formView.config.FormNoneConfig
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.ktx.attrColor
import com.google.android.material.shape.MaterialShapeDrawable

class FormCardM3Style(config: AbstractFormConfig = FormNoneConfig()) :
    AbstractShapeAppearanceFormStyle(config) {

    override fun onBindStyle(holder: FormItemViewHolder, item: BaseForm<*>) {
        val shapeDrawable = MaterialShapeDrawable(getShapeAppearanceModel(holder.itemView, item))
        shapeDrawable.fillColor = ColorStateList.valueOf(
            holder.itemView.attrColor(com.google.android.material.R.attr.colorSurfaceContainerLow)
        )
        holder.itemView.background = shapeDrawable
    }
}