package com.chooongg.formView.data

import com.chooongg.formView.item.AbstractFormItem

interface IFormGroupData {

    fun getItems(): MutableList<AbstractFormItem<*>>

    fun addItem(item: AbstractFormItem<*>) = getItems().add(item)
}