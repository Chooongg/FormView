package com.chooongg.formView.itemProvider

import android.view.ViewGroup
import com.chooongg.formView.FormManager
import com.chooongg.formView.R
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.textview.MaterialTextView

class FormTextProvider : AbstractFormItemProvider() {
    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ) = MaterialTextView(parent.context).apply {
        id = R.id.formContentView
        formTextAppearance(R.attr.formTextAppearanceContent)
        setPaddingRelative(
            style.padding.startMedium,
            style.padding.topMedium,
            style.padding.endMedium,
            style.padding.bottomMedium
        )
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>
    ) {
        with(holder.getView<MaterialTextView>(R.id.formContentView)) {
            gravity = obtainContentGravity(holder, item)
            text = FormManager.parseText(context, item.getContentText())
        }
    }
}