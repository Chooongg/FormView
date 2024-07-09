package com.chooongg.formView.data

import androidx.annotation.MenuRes
import com.chooongg.formView.FormOnMenuCreatedListener
import com.chooongg.formView.FormOnMenuItemClickListener
import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.item.InternalFormGroupTitle

abstract class AbstractGroupData : AbstractFormId(), IFormName, IFormMenu {

    @MenuRes
    override var menu: Int? = null

    override var menuVisibilityMode: FormVisibilityMode = FormVisibilityMode.ENABLED

    override var menuEnableMode: FormEnableMode = FormEnableMode.ENABLED

    override var onMenuCreatedListener: FormOnMenuCreatedListener? = null

    override var onMenuItemClickListener: FormOnMenuItemClickListener? = null

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