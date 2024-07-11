package com.chooongg.formView.item

import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.InternalFormGroupTitleProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

open class InternalFormGroupTitle<CONTENT> : AbstractFormItem<CONTENT>(null, null, null) {

    override var loneLine: Boolean = true

    override var fillEdgesPadding: Boolean = false

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> =
        InternalFormGroupTitleProvider::class
}