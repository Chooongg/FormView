package com.chooongg.formView.item

import androidx.annotation.StyleRes
import com.chooongg.formView.FormTypesetProviderBlock
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormButtonProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormDivider : AbstractFormItem<Any>(null, null, null) {

    @StyleRes
    var dividerStyle: Int? = null

    override var loneLine: Boolean = true

    @Deprecated("Disabled")
    override var showAtEdge: Boolean = false

    @Deprecated("Disabled")
    override var fillEdgesPadding: Boolean = false

    @Deprecated("Disabled")
    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    @Deprecated("Disabled")
    override var typesetProvider: FormTypesetProviderBlock? = null

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormButtonProvider::class
    }
}