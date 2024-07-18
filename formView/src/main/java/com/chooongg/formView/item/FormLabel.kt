package com.chooongg.formView.item

import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormLabelProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormLabel(name: Any?) : AbstractFormItem<Any>(name, null, null) {

    override var enableMode: FormEnableMode = FormEnableMode.ALWAYS

    override var newLine: Boolean = true

    override var loneLine: Boolean = true

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormLabelProvider::class
    }
}