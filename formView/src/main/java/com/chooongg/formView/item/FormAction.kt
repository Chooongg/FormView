package com.chooongg.formView.item

import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormActionProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormAction(
    name: Any?, field: String?, content: Any?
) : AbstractFormItem<Any>(name, field, content) {

    override var fillEdgesPadding: Boolean = false

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormActionProvider::class
    }
}