package com.chooongg.formView.config

import android.view.Gravity
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.chooongg.formView.typeset.HorizontalFormTypeset

class FormGlobalConfig {

    var emsSize: FormEmsSize = FormEmsSize(5)

    var contentGravity: FormContentGravity = FormContentGravity(Gravity.END, Gravity.START)

    var typeset: AbstractFormTypeset = HorizontalFormTypeset()
}