package com.chooongg.formView.style

import android.graphics.drawable.RippleDrawable
import android.view.View
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrColorStateList
import com.google.android.material.shape.MaterialShapeDrawable

class FormNoneStyle : AbstractFormStyle() {

    @Deprecated("Disable")
    override var isIndependentItem: Boolean = false

    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = false
    override fun isFillHorizontalMargin() = false
    override fun isFillVerticalPadding(): Boolean = false
    override fun isFillHorizontalPadding(): Boolean = false

    override fun getForeground(view: View, item: AbstractFormItem<*>) = RippleDrawable(
        view.attrColorStateList(com.google.android.material.R.attr.colorControlHighlight)!!,
        null,
        MaterialShapeDrawable(shapeAppearanceModel)
    )
}