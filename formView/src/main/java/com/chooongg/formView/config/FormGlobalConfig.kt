package com.chooongg.formView.config

import android.view.Gravity
import androidx.annotation.DimenRes
import com.chooongg.formView.R
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.formatter.name.NormalFormNameFormatter
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.chooongg.formView.typeset.HorizontalFormTypeset
import com.google.android.material.button.MaterialButton

class FormGlobalConfig {

    /**
     * 名称格式化程序
     */
    var nameFormatter: FormNameFormatter = NormalFormNameFormatter()

    /**
     * EMS 值
     */
    var emsSize: FormEmsSize = FormEmsSize(5)

    /**
     * 名称图表重力
     */
    @MaterialButton.IconGravity
    var nameIconGravity: Int = MaterialButton.ICON_GRAVITY_TEXT_START

    /**
     * 内容重力
     */
    var contentGravity: FormContentGravity = FormContentGravity(Gravity.END, Gravity.START)

    /**
     * 排版
     */
    var typeset: AbstractFormTypeset = HorizontalFormTypeset()

    /**
     * 项目最大宽度
     */
    @DimenRes
    val itemMaxWidth: Int = R.dimen.formItemMaxWidth
}