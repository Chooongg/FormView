package com.chooongg.formView.itemProvider

import android.view.ViewGroup
import com.chooongg.formView.FormManager
import com.chooongg.formView.R
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.textview.MaterialTextView

class FormTextProvider : AbstractFormItemProvider() {
    override fun onCreateViewHolder(style: AbstractFormStyle, parent: ViewGroup) =
        MaterialTextView(parent.context).apply {
            id = R.id.formContentView
            formTextAppearance(R.attr.formTextAppearanceContent)
            setPaddingRelative(
                style.paddingInfo.startMedium,
                style.paddingInfo.topMedium,
                style.paddingInfo.endMedium,
                style.paddingInfo.bottomMedium
            )
        }

    override fun onBindViewHolder(holder: FormItemViewHolder, item: BaseForm<*>, enabled: Boolean) {
        with(holder.getView<MaterialTextView>(R.id.formContentView)) {
            text = FormManager.parseText(context, item.getContentText())
        }
    }
}