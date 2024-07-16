package com.chooongg.formView.item

import androidx.annotation.StyleRes
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormButtonProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormButton(
    name: Any?, field: String?
) : AbstractFormItem<Any>(name, field, null) {

    @StyleRes
    var buttonStyle: Int? = null

    override val disableTypesetConfigMenu: Boolean = true

    override var menuVisibilityMode: FormVisibilityMode = FormVisibilityMode.NEVER

    @Deprecated("Disabled")
    override var fillEdgesPadding: Boolean = false

    @Deprecated("Disabled")
    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormButtonProvider::class
    }
}