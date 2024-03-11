package com.chooongg.form.data

import com.chooongg.form.FormOnMenuCreatedListener
import com.chooongg.form.FormOnMenuItemClickListener
import com.chooongg.form.enum.FormEnableMode
import com.chooongg.form.enum.FormVisibilityMode

class FormGroupData : AbstractId(), IFormMenu {
    override var menu: Int? = null
    override var menuVisibilityMode: FormVisibilityMode = FormVisibilityMode.ENABLED
    override var menuEnableMode: FormEnableMode = FormEnableMode.ENABLED
    override var onMenuCreatedListener: FormOnMenuCreatedListener? = null
    override var onMenuItemClickListener: FormOnMenuItemClickListener? = null
}