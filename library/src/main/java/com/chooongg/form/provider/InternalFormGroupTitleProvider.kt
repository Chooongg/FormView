package com.chooongg.form.provider

import android.view.View
import android.view.ViewGroup
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.item.BaseForm
import com.chooongg.form.style.AbstractStyle
import kotlinx.coroutines.CoroutineScope

class InternalFormGroupTitleProvider : AbstractFormProviderGroupTitle() {
    override fun onCreateViewHolder(style: AbstractStyle, parent: ViewGroup): View {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        scope: CoroutineScope,
        holder: FormViewHolder,
        view: View,
        item: BaseForm<*>,
        adapterEnabled: Boolean
    ) {
        TODO("Not yet implemented")
    }
}