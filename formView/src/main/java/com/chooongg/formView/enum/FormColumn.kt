package com.chooongg.formView.enum

import androidx.annotation.IntRange
import com.chooongg.formView.FormColumnBlock
import com.chooongg.formView.FormManager

class FormColumn internal constructor(
    @IntRange(1, FormManager.FORM_MAX_COLUMN_COUNT.toLong()) private val column: Int?,
    private val provider: FormColumnBlock?
) {
    constructor(column: Int) : this(column, null)
    constructor(provider: FormColumnBlock) : this(null, provider)

    fun obtain(columnCount: Int): Int {
        return when {
            column != null -> column
            provider != null -> provider.invoke(columnCount)
            else -> FormManager.FORM_EMS
        }
    }
}