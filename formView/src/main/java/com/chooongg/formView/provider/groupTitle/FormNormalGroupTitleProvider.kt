package com.chooongg.formView.provider.groupTitle

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle

class FormNormalGroupTitleProvider : AbstractGroupTitleProvider() {
    override fun onCreateViewHolder(style: AbstractFormStyle, parent: ViewGroup): View {
        return View(parent.context)
    }

    override fun onBindViewHolder(holder: FormItemViewHolder, item: BaseForm<*>, enabled: Boolean) {
    }
}