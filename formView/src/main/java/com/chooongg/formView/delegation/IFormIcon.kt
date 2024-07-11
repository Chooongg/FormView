package com.chooongg.formView.delegation

import com.chooongg.formView.FormColorStateListBlock
import com.google.android.material.button.MaterialButton

interface IFormIcon {

    /**
     * 图标重力
     */
    @MaterialButton.IconGravity
    var iconGravity: Int?

    /**
     * 图标
     */
    var icon: Any?

    /**
     * 图标着色
     */
    var iconTint: FormColorStateListBlock?

    /**
     * 图标大小
     */
    var iconSize: Int?
}