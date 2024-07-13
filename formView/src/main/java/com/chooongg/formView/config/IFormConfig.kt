package com.chooongg.formView.config

import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.itemDelegation.IFormEms
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nestedTitle.AbstractNestedTitleProvider
import com.google.android.material.button.MaterialButton.IconGravity

interface IFormConfig : IFormEms {

    var nameFormatter: FormNameFormatter?

    var groupTitleProvider: AbstractGroupTitleProvider?

    var childTitleProvider: AbstractGroupTitleProvider?

    var nestedTitleProvider: AbstractNestedTitleProvider?

    @IconGravity
    var nameIconGravity: Int?

    var nameGravity: FormGravity?

    var contentGravity: FormGravity?

    var typeset: FormTypeset?

    fun equalsConfig(other: Any?): Boolean
}