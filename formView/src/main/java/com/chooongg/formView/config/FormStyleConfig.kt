package com.chooongg.formView.config

import com.chooongg.formView.FormTypesetProviderBlock
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nested.AbstractNestedProvider

open class FormStyleConfig(
    override val _nameFormatter: FormNameFormatter? = null,
    override val _groupTitleProvider: AbstractGroupTitleProvider? = null,
    override val _childTitleProvider: AbstractGroupTitleProvider? = null,
    override val _detailTitleProvider: AbstractNestedProvider? = null,
    override val _emsSize: FormEmsSize? = null,
    override val _nameIconGravity: Int? = null,
    override val _contentGravity: FormContentGravity? = null,
    override val _isIndependentItem: Boolean? = null,
    override val _typeset: FormTypeset? = null,
    override val _typesetProvider: FormTypesetProviderBlock? = null,
) : AbstractFormStyleConfig()