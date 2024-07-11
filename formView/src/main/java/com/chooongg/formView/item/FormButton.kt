package com.chooongg.formView.item

import android.view.Gravity
import androidx.annotation.StyleRes
import com.chooongg.formView.delegation.FormIconImpl
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.delegation.IFormIcon
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormButtonProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormButton(
    name: Any?, field: String?
) : BaseForm<Any>(name, field, null), IFormIcon by FormIconImpl() {

    @StyleRes
    var buttonStyle: Int? = null

    override var gravity: Int? = Gravity.CENTER

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormButtonProvider::class
    }
}