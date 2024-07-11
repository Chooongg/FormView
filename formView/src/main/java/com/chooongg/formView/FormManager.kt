package com.chooongg.formView

import android.content.Context
import com.chooongg.formView.config.FormGlobalConfig
import com.chooongg.formView.parser.FormIconParser
import com.chooongg.formView.parser.FormTextParser
import com.chooongg.formView.parser.NormalFormIconParser
import com.chooongg.formView.parser.NormalFormTextParser
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.style.FormAlignedStyle

object FormManager {

    const val FORM_SPAN_COUNT = 27720

    const val FLAG_PAYLOAD_UPDATE_CONTENT = "form_flag_update_content"
    const val FLAG_PAYLOAD_UPDATE_BOUNDARY = "form_flag_update_boundary"
    const val FLAG_PAYLOAD_ERROR_NOTIFY = "form_flag_error_notify"

    /**
     * 全局配置
     */
    val globalConfig = FormGlobalConfig()

    /**
     * 居中平滑滚动
     */
    var centerSmoothScroll: Boolean = true

    /**
     * 默认样式
     */
    var defaultStyle: AbstractFormStyle = FormAlignedStyle()

    /**
     * 文本解析器
     */
    var textParser: FormTextParser = NormalFormTextParser()

    /**
     * 图标解析器
     */
    var iconParser: FormIconParser = NormalFormIconParser()

    fun parseText(context: Context, text: Any?) = textParser.parse(context, text)

    fun parseIcon(context: Context, icon: Any?) = iconParser.parse(context, icon)
}