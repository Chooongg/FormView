package com.chooongg.formView.item

import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormTextProvider
import com.chooongg.formView.part.AbstractFormPart
import kotlin.reflect.KClass

class FormText(
    name: Any?, field: String?, content: Any?
) : BaseForm<Any>(name, field, content) {

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormTextProvider::class
    }
}