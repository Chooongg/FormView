package com.chooongg.formView.itemProvider

import android.view.ViewGroup
import android.widget.Space
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle

class FormPlaceHolderProvider : AbstractFormItemProvider() {

    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ) = Space(parent.context)

    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>, enabled: Boolean
    ) = Unit
}