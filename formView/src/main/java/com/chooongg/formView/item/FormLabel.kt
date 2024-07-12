package com.chooongg.formView.item

import androidx.annotation.StyleRes
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormButtonProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormLabel(name: Any?) : AbstractFormItem<Any>(name, null, null) {

    @StyleRes
    var buttonStyle: Int? = null

    override var fixedTypeset: KClass<out AbstractFormTypeset>? = FormNoneTypeset::class

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormButtonProvider::class
    }
}