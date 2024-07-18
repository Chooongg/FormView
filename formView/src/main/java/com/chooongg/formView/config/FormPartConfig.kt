package com.chooongg.formView.config

import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nestedTitle.AbstractNestedTitleProvider
import com.chooongg.formView.style.AbstractFormStyle

class FormPartConfig(
    override var nameFormatter: FormNameFormatter? = null,
    override var groupTitleProvider: AbstractGroupTitleProvider? = null,
    override var childTitleProvider: AbstractGroupTitleProvider? = null,
    override var nestedTitleProvider: AbstractNestedTitleProvider? = null,
    override var emsSize: FormEmsSize? = null,
    override var nameIconGravity: Int? = null,
    override var nameGravity: FormGravity? = null,
    override var contentGravity: FormGravity? = null,
    override var typeset: FormTypeset? = null,
    override var column: FormColumn? = null,
) : IFormPartConfig {
    override fun equalsConfig(other: Any?): Boolean {
        if (other !is IFormPartConfig) return false
        if (other.nameFormatter != nameFormatter) return false
        if (other.groupTitleProvider != groupTitleProvider) return false
        if (other.childTitleProvider != childTitleProvider) return false
        if (other.nestedTitleProvider != nestedTitleProvider) return false
        if (other.emsSize != emsSize) return false
        if (other.nameIconGravity != nameIconGravity) return false
        if (other.nameGravity != nameGravity) return false
        if (other.contentGravity != contentGravity) return false
        if (other.typeset != typeset) return false
        if (other.column != column) return false
        return true
    }
}