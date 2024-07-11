package com.chooongg.formView.data

import com.chooongg.formView.item.BaseForm

interface IFormGroupData {

    fun getItems(): MutableList<BaseForm<*>>

    fun addItem(item: BaseForm<*>) = getItems().add(item)
}