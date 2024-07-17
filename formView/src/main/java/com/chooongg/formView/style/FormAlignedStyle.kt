package com.chooongg.formView.style

import android.graphics.drawable.RippleDrawable
import android.view.View
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrColorStateList
import com.google.android.material.shape.MaterialShapeDrawable

class FormAlignedStyle() : AbstractFormStyle() {

    @Deprecated("Disable")
    override var isIndependentItem: Boolean = false

    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = true
    override fun isFillVerticalPadding(): Boolean = false
}