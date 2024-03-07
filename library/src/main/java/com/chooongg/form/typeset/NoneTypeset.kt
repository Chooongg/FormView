package com.chooongg.form.typeset

import android.view.View
import android.view.ViewGroup
import com.chooongg.form.enum.FormEmsMode
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.item.BaseForm
import com.chooongg.form.style.AbstractStyle

class NoneTypeset : AbstractTypeset() {

    override var emsMode: FormEmsMode = FormEmsMode(FormEmsMode.NONE)

    override fun onCreateViewHolder(style: AbstractStyle, parent: ViewGroup): ViewGroup? = null

    override fun addView(style: AbstractStyle, parent: ViewGroup, child: View) = Unit

    override fun onBindViewHolder(
        holder: FormViewHolder, item: BaseForm<*>, layout: ViewGroup, adapterEnabled: Boolean
    ) = Unit
}