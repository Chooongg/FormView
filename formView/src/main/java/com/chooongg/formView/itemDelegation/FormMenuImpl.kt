package com.chooongg.formView.itemDelegation

import com.chooongg.formView.FormOnMenuCreatedListener
import com.chooongg.formView.FormOnMenuItemClickListener
import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormVisibilityMode

class FormMenuImpl : IFormMenu {
    override var menu: Int? = null
    override var menuVisibilityMode: FormVisibilityMode = FormVisibilityMode.ALWAYS
    override var menuEnableMode: FormEnableMode = FormEnableMode.ENABLED
    override var onMenuCreatedListener: FormOnMenuCreatedListener? = null
    override var onMenuItemClickListener: FormOnMenuItemClickListener? = null
}