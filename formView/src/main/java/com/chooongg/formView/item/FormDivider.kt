package com.chooongg.formView.item

import androidx.annotation.DimenRes
import androidx.annotation.StyleRes
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormDividerProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormDivider : AbstractFormItem<Any>(null, null, null) {

    @StyleRes
    var dividerStyle: Int? = null

    var insetStartFromPadding: Boolean = false

    var insetEndFromPadding: Boolean = false

    @DimenRes
    var insetStartResId: Int? = null

    @DimenRes
    var insetEndResId: Int? = null

    override val disableTypesetConfigMenu: Boolean = true

    override var loneLine: Boolean = true

    override var showAtEdge: Boolean = false

    override var fillEdgesPadding: Boolean = false

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormDividerProvider::class
    }
}