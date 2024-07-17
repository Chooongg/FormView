package com.chooongg.formView.data

import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.itemDelegation.IFormName

interface IFormPart : IFormName {
    var visibilityMode: FormVisibilityMode
    var column: FormColumn?

    fun isVisible(adapterEnabled: Boolean): Boolean {
        return when (visibilityMode) {
            FormVisibilityMode.ALWAYS -> true
            FormVisibilityMode.ENABLED -> adapterEnabled
            FormVisibilityMode.DISABLED -> !adapterEnabled
            FormVisibilityMode.NEVER -> false
        }
    }
}