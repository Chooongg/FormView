package com.chooongg.formView.enum

import android.view.Gravity
import androidx.annotation.GravityInt
import com.chooongg.formView.FormGravityBlock

/**
 * 内容重力
 */
data class FormGravity internal constructor(
    @GravityInt private val gravity: Int?, private val provider: FormGravityBlock?
) {
    constructor(gravity: Int) : this(gravity, null)
    constructor(provider: FormGravityBlock) : this(null, provider)

    fun obtain(columnCount: Int, columnSize: Int): Int {
        return when {
            gravity != null -> gravity
            provider != null -> provider.invoke(columnCount, columnSize)
            else -> Gravity.NO_GRAVITY
        }
    }
}