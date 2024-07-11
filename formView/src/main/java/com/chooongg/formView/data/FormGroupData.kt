package com.chooongg.formView.data

import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.InternalFormGroupTitle
import com.chooongg.formView.itemDelegation.FormIconImpl
import com.chooongg.formView.itemDelegation.FormMenuImpl
import com.chooongg.formView.itemDelegation.FormNameImpl
import com.chooongg.formView.itemDelegation.IFormIcon
import com.chooongg.formView.itemDelegation.IFormMenu
import com.chooongg.formView.itemDelegation.IFormName

open class FormGroupData : AbstractFormId(), IFormGroupData, IFormName by FormNameImpl(),
    IFormIcon by FormIconImpl(), IFormMenu by FormMenuImpl() {

    private val _items = mutableListOf<AbstractFormItem<*>>()

    override fun getItems(): MutableList<AbstractFormItem<*>> = _items

    private var _groupTitleItem: InternalFormGroupTitle<Any>? = null

    open fun getGroupTitleItem(): InternalFormGroupTitle<*>? {
        return if (name != null) {
            if (_groupTitleItem == null) _groupTitleItem = InternalFormGroupTitle()
            _groupTitleItem!!.also {
                it.name = name
                it.icon = icon
                it.iconGravity = iconGravity
                it.iconTint = iconTint
                it.iconSize = iconSize
                it.menu = menu
                it.menuVisibilityMode = menuVisibilityMode
                it.menuEnableMode = menuEnableMode
                it.onMenuCreatedListener = onMenuCreatedListener
                it.onMenuItemClickListener = onMenuItemClickListener
            }
        } else {
            _groupTitleItem = null
            null
        }
    }
}