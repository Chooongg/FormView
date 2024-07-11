package com.chooongg.formView.item

import android.view.Gravity
import androidx.annotation.StyleRes
import com.chooongg.formView.delegation.FormMenuImpl
import com.chooongg.formView.delegation.IFormMenu
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormButtonProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormLabel(name: Any?) : BaseForm<Any>(name, null, null), IFormMenu by FormMenuImpl() {

    @StyleRes
    var buttonStyle: Int? = null

    override var gravity: Int? = Gravity.CENTER

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormButtonProvider::class
    }
}