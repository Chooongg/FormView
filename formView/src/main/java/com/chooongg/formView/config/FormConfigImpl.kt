package com.chooongg.formView.config

import com.chooongg.formView.FormTypesetProviderBlock
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nested.AbstractNestedProvider

class FormConfigImpl : IFormConfig {
    override var nameFormatter: FormNameFormatter? = null
    override var groupTitleProvider: AbstractGroupTitleProvider? = null
    override var childTitleProvider: AbstractGroupTitleProvider? = null
    override var nestedTitleProvider: AbstractNestedProvider? = null
    override var emsSize: FormEmsSize? = null
    override var nameIconGravity: Int? = null
    override var contentGravity: FormContentGravity? = null
    override var typeset: FormTypesetProviderBlock? = null
    override var isIndependentItem: Boolean = false

    override fun equalsConfig(other: Any?): Boolean {
        if (other !is IFormConfig) return false
        if (other.nameFormatter != nameFormatter) return false
        if (other.groupTitleProvider != groupTitleProvider) return false
        if (other.childTitleProvider != childTitleProvider) return false
        if (other.nestedTitleProvider != nestedTitleProvider) return false
        if (other.emsSize != emsSize) return false
        if (other.nameIconGravity != nameIconGravity) return false
        if (other.contentGravity != contentGravity) return false
        if (other.typeset != typeset) return false
        if (other.isIndependentItem != isIndependentItem) return false
        return true
    }
}