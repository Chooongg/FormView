package com.chooongg.formView.item

import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.InternalFormNoneProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class InternalFormNone internal constructor(spanIndex: Int, spanSize: Int) :
    BaseForm<Any>(null, null, null) {

    override val id: String = ""
    override var typeset: AbstractFormTypeset? = FormNoneTypeset()

    init {
        this.spanIndex = spanIndex
        this.spanSize = spanSize
    }

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> =
        InternalFormNoneProvider::class
}