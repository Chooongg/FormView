package com.chooongg.formView.config

import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.typeset.AbstractFormTypeset

class FormNoneConfig(
    override val _emsSize: FormEmsSize? = null,
    override val _contentGravity: FormContentGravity? = null,
    override val _typeset: AbstractFormTypeset? = null
) : AbstractFormConfig()