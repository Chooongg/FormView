package com.chooongg.formView.item

import androidx.annotation.MenuRes
import com.chooongg.form.FormOnMenuCreatedListener
import com.chooongg.form.FormOnMenuItemClickListener
import com.chooongg.form.data.AbstractFormId
import com.chooongg.formView.data.IFormIcon
import com.chooongg.formView.data.IFormMenu
import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormVisibilityMode

abstract class AbstractForm : AbstractFormId(), IFormIcon, IFormMenu {

    //<editor-fold desc="基础 Basic">

    /**
     * 可见模式
     */
    var visibilityMode: FormVisibilityMode = FormVisibilityMode.ALWAYS

    /**
     * 启用模式
     */
    var enableMode: FormEnableMode = FormEnableMode.ENABLED

    @MenuRes
    override var menu: Int? = null

    override var menuVisibilityMode: FormVisibilityMode = FormVisibilityMode.ALWAYS

    override var menuEnableMode: FormEnableMode = FormEnableMode.ENABLED

    override var onMenuCreatedListener: FormOnMenuCreatedListener? = null

    override var onMenuItemClickListener: FormOnMenuItemClickListener? = null

    /**
     * 是否可见
     */
    fun isVisible(adapterEnabled: Boolean): Boolean {
        return when (visibilityMode) {
            FormVisibilityMode.ALWAYS -> true
            FormVisibilityMode.ENABLED -> adapterEnabled
            FormVisibilityMode.DISABLED -> !adapterEnabled
            FormVisibilityMode.NEVER -> false
        }
    }

    /**
     * 是否可用
     */
    fun isEnable(adapterEnabled: Boolean): Boolean {
        return when (enableMode) {
            FormEnableMode.ALWAYS -> true
            FormEnableMode.ENABLED -> adapterEnabled
            FormEnableMode.DISABLED -> !adapterEnabled
            FormEnableMode.NEVER -> false
        }
    }
    //</editor-fold>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractForm) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}