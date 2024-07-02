package com.chooongg.formView.data

import com.google.android.material.button.MaterialButton

interface IFormIcon {

    /**
     * 图标
     */
    var icon: Int?

    /**
     * 图标着色
     */
    var iconTint: FormColorStateListBlock?

    /**
     * 图标重力
     */
    @MaterialButton.IconGravity
    var iconGravity: Int

    /**
     * 图标大小
     */
    var iconSize: Int?
}