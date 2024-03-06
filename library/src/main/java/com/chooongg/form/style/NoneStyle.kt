package com.chooongg.form.style

import android.view.View
import android.view.ViewGroup
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.item.BaseForm

class NoneStyle : AbstractStyle() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewGroup? = null

    override fun addView(parent: ViewGroup, child: View) = Unit

    override fun onBindViewHolder(
        holder: FormViewHolder,
        item: BaseForm<*>,
        layout: ViewGroup?,
        adapterEnabled: Boolean
    ) = Unit
}