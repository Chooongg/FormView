package com.chooongg.form.item

import com.chooongg.form.part.AbstractPart
import com.chooongg.form.provider.AbstractFormProvider
import com.chooongg.form.typeset.AbstractTypeset
import com.chooongg.form.typeset.NoneTypeset
import kotlin.reflect.KClass

class InternalFormGroupTitle(name: Any?, field: String?) : BaseForm<Any>(name, field) {

    override var loneLine: Boolean = true

    override var fillEdges: Boolean = false

    override var typeset: AbstractTypeset? = NoneTypeset()

    override fun getProvider(part: AbstractPart<*>): KClass<out AbstractFormProvider> {
        return part.style.config.groupTitleProvider
    }

}