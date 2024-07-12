package com.chooongg.formView.enum

import com.chooongg.formView.FormManager
import com.chooongg.formView.typeset.AbstractFormTypeset
import kotlin.reflect.KClass

data class FormTypeset(
    val typeset: KClass<out AbstractFormTypeset>,
    val multipleTypeset: KClass<out AbstractFormTypeset> = typeset
)