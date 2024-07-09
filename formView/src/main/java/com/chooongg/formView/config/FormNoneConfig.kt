package com.chooongg.formView.config

import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nested.AbstractNestedProvider
import com.chooongg.formView.typeset.AbstractFormTypeset

class FormNoneConfig(
    override val _nameFormatter: FormNameFormatter? = null,
    override val _groupTitleProvider: AbstractGroupTitleProvider? = null,
    override val _nestedGroupTitleProvider: AbstractGroupTitleProvider? = null,
    override val _detailTitleProvider: AbstractNestedProvider? = null,
    override val _emsSize: FormEmsSize? = null,
    override val _nameIconGravity: Int? = null,
    override val _contentGravity: FormContentGravity? = null,
    override val _typeset: AbstractFormTypeset? = null,
    override val _itemMaxWidth: Int? = null,
) : AbstractFormConfig()