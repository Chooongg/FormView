package com.chooongg.formView.itemDelegation

import android.view.Gravity
import androidx.annotation.GravityInt

class FormNameImpl : IFormName {
    override var name: Any? = null

    @GravityInt
    override var nameGravity: Int? = null
}