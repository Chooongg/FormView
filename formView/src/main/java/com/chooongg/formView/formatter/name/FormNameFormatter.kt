package com.chooongg.formView.formatter.name

import android.content.Context
import com.chooongg.formView.item.AbstractFormItem

abstract class FormNameFormatter {

    abstract fun format(context: Context, item: AbstractFormItem<*>): CharSequence?

    override fun equals(other: Any?): Boolean {
        return other is FormNameFormatter && other.javaClass == javaClass
    }

    override fun hashCode(): Int = javaClass.hashCode()
}