package com.chooongg.formView.data

import com.chooongg.formView.config.FormPartConfig
import com.chooongg.formView.config.IFormPartConfig
import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.itemDelegation.FormFieldImpl
import com.chooongg.formView.itemDelegation.IFormField
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nestedTitle.AbstractNestedTitleProvider

class FormPartData(
    nameFormatter: FormNameFormatter? = null,
    groupTitleProvider: AbstractGroupTitleProvider? = null,
    childTitleProvider: AbstractGroupTitleProvider? = null,
    nestedTitleProvider: AbstractNestedTitleProvider? = null,
    emsSize: FormEmsSize? = null,
    nameIconGravity: Int? = null,
    nameGravity: FormGravity? = null,
    contentGravity: FormGravity? = null,
    typeset: FormTypeset? = null,
    column: FormColumn? = null,
    block: FormPartData.() -> Unit
) : FormGroupData(), IFormPart, IFormField by FormFieldImpl() {

    override val partConfig: IFormPartConfig = FormPartConfig(
        nameFormatter,
        groupTitleProvider,
        childTitleProvider,
        nestedTitleProvider,
        emsSize,
        nameIconGravity,
        nameGravity,
        contentGravity,
        typeset,
        column
    )

    override var visibilityMode: FormVisibilityMode = FormVisibilityMode.ALWAYS

    init {
        block.invoke(this)
    }
}