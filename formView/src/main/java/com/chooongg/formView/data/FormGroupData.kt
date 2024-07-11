package com.chooongg.formView.data

import com.chooongg.formView.FormColorStateListBlock
import com.chooongg.formView.FormOnMenuCreatedListener
import com.chooongg.formView.FormOnMenuItemClickListener
import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.delegation.IFormIcon
import com.chooongg.formView.delegation.IFormMenu
import com.chooongg.formView.delegation.IFormName
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.item.InternalFormGroupTitle
import com.google.android.material.button.MaterialButton

open class FormGroupData : AbstractFormId(), IFormGroupData, IFormName, IFormIcon, IFormMenu {

    private val _items = mutableListOf<BaseForm<*>>()

    override var name: Any? = null
    override var field: String? = null

    @MaterialButton.IconGravity
    override var iconGravity: Int? = null
    override var icon: Any? = null
    override var iconTint: FormColorStateListBlock? = null
    override var iconSize: Int? = null

    override var menu: Int? = null
    override var menuVisibilityMode: FormVisibilityMode = FormVisibilityMode.ENABLED
    override var menuEnableMode: FormEnableMode = FormEnableMode.ENABLED
    override var onMenuCreatedListener: FormOnMenuCreatedListener? = null
    override var onMenuItemClickListener: FormOnMenuItemClickListener? = null

    override fun getItems(): MutableList<BaseForm<*>> = _items

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