package com.chooongg.formView.itemDelegation

import androidx.annotation.StyleRes
import com.chooongg.formView.enum.FormGravity

interface IFormName {

    /**
     * 名称
     */
    var name: Any?

    /**
     * 名称重力
     */
    var nameGravity: FormGravity?

    /**
     * 名称文本外观
     */
    @setparam:StyleRes
    var nameTextAppearance: Int?
}