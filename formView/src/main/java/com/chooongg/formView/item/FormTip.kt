package com.chooongg.formView.item

import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormTipProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormTip(name: Any?) : AbstractFormItem<Any>(name, null, null) {

    override val disableTypesetConfigMenu: Boolean = true

    override var enableMode: FormEnableMode = FormEnableMode.ALWAYS

    override var loneLine: Boolean = true

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormTipProvider::class
    }
}