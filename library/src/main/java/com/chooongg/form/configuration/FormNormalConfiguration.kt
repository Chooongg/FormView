package com.chooongg.form.configuration

import com.chooongg.form.enum.FormContentGravity
import com.chooongg.form.enum.FormEmsSize
import com.chooongg.form.typeset.AbstractTypeset

class FormNormalConfiguration : AbstractFormConfiguration() {
    override val _nameFormatter: AbstractNameFormatter? = null
    override val _groupTitleProvider: AbstractGroupTitleProvider? = null
    override val _detailTitleProvider: AbstractDetailProvider? = null
    override val _emsSize: FormEmsSize? = null
    override val _contentGravity: FormContentGravity? = null
    override val _typeset: AbstractTypeset? = null
}