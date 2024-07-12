package com.chooongg.formView.config

import com.chooongg.formView.FormTypesetProviderBlock
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.itemDelegation.IFormEms
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nested.AbstractNestedProvider
import com.google.android.material.button.MaterialButton.IconGravity

interface IFormConfig : IFormEms {

    var nameFormatter: FormNameFormatter?

    var groupTitleProvider: AbstractGroupTitleProvider?

    var childTitleProvider: AbstractGroupTitleProvider?

    var nestedTitleProvider: AbstractNestedProvider?

    @IconGravity
    var nameIconGravity: Int?

    var contentGravity: FormContentGravity?

    var typeset: FormTypesetProviderBlock?

    var isIndependentItem: Boolean

    fun equalsConfig(other: Any?): Boolean
}