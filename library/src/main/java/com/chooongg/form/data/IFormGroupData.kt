package com.chooongg.form.data

import com.chooongg.form.item.BaseForm

interface IFormGroupData {

    fun getItems(): MutableList<BaseForm<*>>

    fun addItem(item: BaseForm<*>) = getItems().add(item)

}