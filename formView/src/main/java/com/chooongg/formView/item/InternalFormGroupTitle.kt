package com.chooongg.formView.item

import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.InternalFormGroupTitleProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

open class InternalFormGroupTitle<CONTENT> : BaseForm<CONTENT>(null, null, null) {

    override var loneLine: Boolean = true

    override var fillEdges: Boolean = false

    override var typeset: AbstractFormTypeset? = FormNoneTypeset()

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> =
        InternalFormGroupTitleProvider::class
}