package com.chooongg.form.configuration

import com.chooongg.form.enum.FormContentGravity
import com.chooongg.form.enum.FormEmsSize
import com.chooongg.form.formatter.name.AbstractNameFormatter
import com.chooongg.form.provider.AbstractFormProviderGroupTitle
import com.chooongg.form.typeset.AbstractTypeset
import kotlin.reflect.KClass

class FormNormalConfiguration : AbstractFormConfiguration() {
    override val _nameFormatter: AbstractNameFormatter? = null
    override val _groupTitleProvider: KClass<out AbstractFormProviderGroupTitle>? = null
//    override val _detailTitleProvider: AbstractDetailProvider? = null
    override val _emsSize: FormEmsSize? = null
    override val _contentGravity: FormContentGravity? = null
    override val _typeset: AbstractTypeset? = null
}