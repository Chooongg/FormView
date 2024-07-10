package com.chooongg.formView.config

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

    protected abstract val _isIndependentItem: Boolean?

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

    val isIndependentItem: Boolean
        get() = _isIndependentItem ?: FormManager.globalConfig.isIndependentItem

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractFormConfig) return false
        if (javaClass != other.javaClass) return false
        if (_nameFormatter != other._nameFormatter) return false
        if (_groupTitleProvider != other._groupTitleProvider) return false
        if (_nestedGroupTitleProvider != other._nestedGroupTitleProvider) return false
        if (_detailTitleProvider != other._detailTitleProvider) return false
        if (_emsSize != other._emsSize) return false
        if (_nameIconGravity != other._nameIconGravity) return false
        if (_contentGravity != other._contentGravity) return false
        if (_typeset != other._typeset) return false
        if (_isIndependentItem != other._isIndependentItem) return false
        return true
    }

    override fun hashCode(): Int {
        var result = _nameFormatter?.hashCode() ?: 0
        result = 31 * result + (_groupTitleProvider?.hashCode() ?: 0)
        result = 31 * result + (_nestedGroupTitleProvider?.hashCode() ?: 0)
        result = 31 * result + (_detailTitleProvider?.hashCode() ?: 0)
        result = 31 * result + (_emsSize?.hashCode() ?: 0)
        result = 31 * result + (_nameIconGravity ?: 0)
        result = 31 * result + (_contentGravity?.hashCode() ?: 0)
        result = 31 * result + (_typeset?.hashCode() ?: 0)
        return result
    }
}