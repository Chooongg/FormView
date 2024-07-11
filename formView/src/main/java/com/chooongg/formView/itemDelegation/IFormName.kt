package com.chooongg.formView.itemDelegation

import androidx.annotation.GravityInt

interface IFormName {

    /**
     * 名字
     */
    var name: Any?

    /**
     * 名字重力
     */
    @setparam:GravityInt
    var nameGravity: Int?
}