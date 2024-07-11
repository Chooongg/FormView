package com.chooongg.formView.config

import com.chooongg.formView.FormManager
import com.chooongg.formView.FormTypesetProviderBlock
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nested.AbstractNestedProvider
import com.google.android.material.button.MaterialButton

@Suppress("PropertyName")
abstract class AbstractFormStyleConfig {

    protected abstract val _nameFormatter: FormNameFormatter?

    protected abstract val _groupTitleProvider: AbstractGroupTitleProvider?

    protected abstract val _childTitleProvider: AbstractGroupTitleProvider?

    protected abstract val _detailTitleProvider: AbstractNestedProvider?

    protected abstract val _emsSize: FormEmsSize?

    @MaterialButton.IconGravity
    protected abstract val _nameIconGravity: Int?

    protected abstract val _contentGravity: FormContentGravity?

    protected abstract val _isIndependentItem: Boolean?

    protected abstract val _typeset: FormTypeset?

    protected abstract val _typesetProvider: FormTypesetProviderBlock?

    val nameFormatter: FormNameFormatter
        get() = _nameFormatter ?: FormManager.globalConfig.nameFormatter

    val groupTitleProvider: AbstractGroupTitleProvider
        get() = _groupTitleProvider ?: FormManager.globalConfig.groupTitleProvider

    val childTitleProvider: AbstractGroupTitleProvider
        get() = _childTitleProvider ?: FormManager.globalConfig.childTitleProvider

    val detailTitleProvider: AbstractNestedProvider
        get() = _detailTitleProvider ?: FormManager.globalConfig.detailTitleProvider

    val emsSize: FormEmsSize
        get() = _emsSize ?: FormManager.globalConfig.emsSize

    val nameIconGravity: Int
        get() = _nameIconGravity ?: FormManager.globalConfig.nameIconGravity

    val contentGravity: FormContentGravity
        get() = _contentGravity ?: FormManager.globalConfig.contentGravity

    val isIndependentItem: Boolean
        get() = _isIndependentItem ?: FormManager.globalConfig.isIndependentItem

    val typeset: FormTypeset
        get() = _typeset ?: FormManager.globalConfig.typeset

    val typesetProvider: FormTypesetProviderBlock?
        get() = _typesetProvider

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractFormStyleConfig) return false
        if (javaClass != other.javaClass) return false
        if (_nameFormatter != other._nameFormatter) return false
        if (_groupTitleProvider != other._groupTitleProvider) return false
        if (_childTitleProvider != other._childTitleProvider) return false
        if (_detailTitleProvider != other._detailTitleProvider) return false
        if (_emsSize != other._emsSize) return false
        if (_nameIconGravity != other._nameIconGravity) return false
        if (_contentGravity != other._contentGravity) return false
        if (_isIndependentItem != other._isIndependentItem) return false
        if (_typeset != other._typeset) return false
        if (_typesetProvider != other._typesetProvider) return false
        return true
    }

    override fun hashCode(): Int {
        var result = _nameFormatter?.hashCode() ?: 0
        result = 31 * result + (_groupTitleProvider?.hashCode() ?: 0)
        result = 31 * result + (_childTitleProvider?.hashCode() ?: 0)
        result = 31 * result + (_detailTitleProvider?.hashCode() ?: 0)
        result = 31 * result + (_emsSize?.hashCode() ?: 0)
        result = 31 * result + (_nameIconGravity ?: 0)
        result = 31 * result + (_contentGravity?.hashCode() ?: 0)
        result = 31 * result + (_isIndependentItem?.hashCode() ?: 0)
        result = 31 * result + (_typeset?.hashCode() ?: 0)
        return result
    }
}