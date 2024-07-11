package com.chooongg.formView.itemDelegation

import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.enum.FormEmsSize

interface IFormEms {

    /**
     * 名称Ems模式
     */
    var emsMode: FormEmsMode?

    /**
     * 名称Ems
     */
    var emsSize: FormEmsSize?
}