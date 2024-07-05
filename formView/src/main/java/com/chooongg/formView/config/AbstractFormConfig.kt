package com.chooongg.formView.config

import com.chooongg.formView.FormManager
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.typeset.AbstractFormTypeset

abstract class AbstractFormConfig {

    abstract val _emsSize: FormEmsSize?

    abstract val _contentGravity: FormContentGravity?

    abstract val _typeset: AbstractFormTypeset?

    val emsSize: FormEmsSize
        get() = _emsSize ?: FormManager.globalConfig.emsSize

    val contentGravity: FormContentGravity
        get() = _contentGravity ?: FormManager.globalConfig.contentGravity

    val typeset: AbstractFormTypeset
        get() = _typeset ?: FormManager.globalConfig.typeset
}