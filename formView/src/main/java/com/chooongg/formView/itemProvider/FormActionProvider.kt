package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.button.MaterialButton

class FormActionProvider : AbstractFormItemProvider() {
    override fun onCreateViewHolder(
        part: AbstractFormPart<*>,
        style: AbstractFormStyle,
        parent: ViewGroup
    ): View {
        return LinearLayoutCompat(parent.context).also {
            it.id = R.id.formTypesetLayout
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
                    style.padding.start,
                    style.padding.top,
                    style.padding.endMedium,
                    style.padding.bottom
                )
            }, LinearLayoutCompat.LayoutParams(-2, -1))
        }
    }

    override fun onBindViewHolder(
        holder: FormViewHolder,
        item: AbstractFormItem<*>,
        enabled: Boolean
    ) {
        holder.getView<MaterialButton>(R.id.formNameView)
    }
}