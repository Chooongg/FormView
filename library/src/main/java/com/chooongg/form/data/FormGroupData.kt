package com.chooongg.form.data

import com.chooongg.form.FormColorStateListBlock
import com.chooongg.form.FormOnMenuCreatedListener
import com.chooongg.form.FormOnMenuItemClickListener
import com.chooongg.form.enum.FormEnableMode
import com.chooongg.form.enum.FormVisibilityMode
import com.chooongg.form.item.BaseForm
import com.chooongg.form.item.InternalFormGroupTitle
import com.google.android.material.button.MaterialButton

open class FormGroupData : AbstractFormId(), IFormGroupData, IFormIcon, IFormMenu {

    private val _items = mutableListOf<BaseForm<*>>()

    override var icon: Int? = null
    override var iconTint: FormColorStateListBlock? = null

    @MaterialButton.IconGravity
    override var iconGravity: Int = MaterialButton.ICON_GRAVITY_TEXT_START
    override var iconSize: Int? = null

    override var menu: Int? = null
    override var menuVisibilityMode: FormVisibilityMode = FormVisibilityMode.ENABLED
    override var menuEnableMode: FormEnableMode = FormEnableMode.ENABLED
    override var onMenuCreatedListener: FormOnMenuCreatedListener? = null
    override var onMenuItemClickListener: FormOnMenuItemClickListener? = null

    private var _groupTitleItem: InternalFormGroupTitle? = null

    open fun getGroupTitleItem(data: IFormPart): InternalFormGroupTitle? {
        return if (data.partName != null) {
            if (_groupTitleItem == null) _groupTitleItem = InternalFormGroupTitle(null, null)
            _groupTitleItem!!.also {
                it.name = data.partName
                it.icon = data.icon
                it.iconGravity = data.iconGravity
                it.iconTint = data.iconTint
                it.iconSize = data.iconSize
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

    override fun getItems(): MutableList<BaseForm<*>> = _items
}