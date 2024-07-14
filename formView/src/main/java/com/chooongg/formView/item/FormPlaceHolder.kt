package com.chooongg.formView.item

import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormPlaceHolderProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

class FormPlaceHolder() : AbstractFormItem<Any>(null, null, null) {

    override val id: String = ""

    override var fillEdgesPadding: Boolean = false

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    internal constructor(columnCount: Int, columnIndex: Int, columnSize: Int) : this() {
        this.columnCount = columnCount
        this.columnIndex = columnIndex
        this.columnSize = columnSize
    }

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> =
        FormPlaceHolderProvider::class
}