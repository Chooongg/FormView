package com.chooongg.formView.enum

import com.chooongg.formView.FormTypesetBlock
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlin.reflect.KClass

data class FormTypeset internal constructor(
    private val typeset: KClass<out AbstractFormTypeset>? = null,
    private val provider: FormTypesetBlock? = null
) {
    constructor(typeset: KClass<out AbstractFormTypeset>) : this(typeset, null)
    constructor(provider: FormTypesetBlock) : this(null, provider)

    fun obtain(columnCount: Int, columnSize: Int): KClass<out AbstractFormTypeset> {
        return when {
            typeset != null -> typeset
            provider != null -> provider.invoke(columnCount, columnSize)
            else -> FormNoneTypeset::class
        }
    }
}