package com.chooongg.formView

import com.chooongg.formView.config.FormGlobalConfig
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.style.FormNoneStyle

object FormManager {

    const val FLAG_PAYLOAD_UPDATE_CONTENT = "form_flag_update_content"
    const val FLAG_PAYLOAD_UPDATE_BOUNDARY = "form_flag_update_boundary"
    const val FLAG_PAYLOAD_ERROR_NOTIFY = "form_flag_error_notify"

    val globalConfig: FormGlobalConfig = FormGlobalConfig()

    /**
     * 居中平滑滚动
     */
    var centerSmoothScroll: Boolean = true

    /**
     * 默认样式
     */
    var defaultStyle: AbstractFormStyle = FormNoneStyle()
}