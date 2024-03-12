package com.chooongg.form.data

interface IFormPart : IFormIcon {
    /**
     * 是否启用片段
     */
    var partEnabled: Boolean

    /**
     * 片段字段
     */
    var partField: String?

    /**
     * 片段名称
     */
    var partName: Any?
}