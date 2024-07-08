package com.chooongg.formView.formatter.name

import android.content.Context
import com.chooongg.formView.item.BaseForm

abstract class FormNameFormatter {

    abstract fun format(context: Context, item: BaseForm<*>): CharSequence?

    override fun equals(other: Any?): Boolean {
        return other is FormNameFormatter && other.javaClass == javaClass
    }

    override fun hashCode(): Int = javaClass.hashCode()
}