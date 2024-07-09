package com.chooongg.formView.config

import androidx.annotation.DimenRes
import com.chooongg.formView.FormManager
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nested.AbstractNestedProvider
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.google.android.material.button.MaterialButton

abstract class AbstractFormConfig {

    protected abstract val _nameFormatter: FormNameFormatter?

    protected abstract val _groupTitleProvider: AbstractGroupTitleProvider?

    protected abstract val _nestedGroupTitleProvider: AbstractGroupTitleProvider?

    protected abstract val _detailTitleProvider: AbstractNestedProvider?

    protected abstract val _emsSize: FormEmsSize?

    @MaterialButton.IconGravity
    protected abstract val _nameIconGravity: Int?

    protected abstract val _contentGravity: FormContentGravity?

    protected abstract val _typeset: AbstractFormTypeset?

    @get:DimenRes
    protected abstract val _itemMaxWidth: Int?

    val nameFormatter: FormNameFormatter
        get() = _nameFormatter ?: FormManager.globalConfig.nameFormatter

    val groupTitleProvider: AbstractGroupTitleProvider
        get() = _groupTitleProvider ?: FormManager.globalConfig.groupTitleProvider

    val nestedGroupTitleProvider: AbstractGroupTitleProvider
        get() = _nestedGroupTitleProvider ?: FormManager.globalConfig.nestedGroupTitleProvider

    val detailTitleProvider: AbstractNestedProvider
        get() = _detailTitleProvider ?: FormManager.globalConfig.detailTitleProvider

    val emsSize: FormEmsSize
        get() = _emsSize ?: FormManager.globalConfig.emsSize

    val nameIconGravity: Int
        get() = _nameIconGravity ?: FormManager.globalConfig.nameIconGravity

    val contentGravity: FormContentGravity
        get() = _contentGravity ?: FormManager.globalConfig.contentGravity

    val typeset: AbstractFormTypeset
        get() = _typeset ?: FormManager.globalConfig.typeset

    val itemMaxWidth: Int
        get() = _itemMaxWidth ?: FormManager.globalConfig.itemMaxWidth

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractFormConfig) return false
        if (other._nameFormatter != _nameFormatter) return false
        if (other._emsSize != _emsSize) return false
        if (other._nameIconGravity != _nameIconGravity) return false
        if (other._contentGravity != _contentGravity) return false
        if (other._typeset != _typeset) return false
        if (other._itemMaxWidth != _itemMaxWidth) return false
        return true
    }

    override fun hashCode(): Int {
        var result = _nameFormatter?.hashCode() ?: 0
        result = 31 * result + (_emsSize?.hashCode() ?: 0)
        result = 31 * result + (_nameIconGravity ?: 0)
        result = 31 * result + (_contentGravity?.hashCode() ?: 0)
        result = 31 * result + (_typeset?.hashCode() ?: 0)
        return result
    }
}