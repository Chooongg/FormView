package com.chooongg.formView.enum

import androidx.annotation.IntRange
import com.chooongg.formView.FormEmsSizeBlock
import com.chooongg.formView.FormManager

/**
 * Ems值
 */
data class FormEmsSize internal constructor(
    @IntRange(from = 1) private val size: Int?, private val provider: FormEmsSizeBlock?
) {
    constructor(size: Int) : this(size, null)
    constructor(provider: FormEmsSizeBlock) : this(null, provider)

    fun obtain(columnCount: Int, columnSize: Int): Int {
        return when {
            size != null -> size
            provider != null -> provider.invoke(columnCount, columnSize)
            else -> FormManager.FORM_EMS
        }
    }
}