package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle

class FormButtonProvider : AbstractFormItemProvider() {

    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View {
        return View(parent.context)
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>
    ) {
    }
}