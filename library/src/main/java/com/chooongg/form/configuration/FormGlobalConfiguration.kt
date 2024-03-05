package com.chooongg.form.configuration

import android.view.Gravity
import com.chooongg.form.enum.FormContentGravity
import com.chooongg.form.enum.FormEmsSize
import com.chooongg.form.formatter.name.AbstractNameFormatter
import com.chooongg.form.formatter.name.NormalNameFormatter
import com.chooongg.form.typeset.AbstractTypeset

/**
 * 全局配置
 */
open class FormGlobalConfiguration {

    /**
     * 名称格式化程序
     */
    open var nameFormatter: AbstractNameFormatter = NormalNameFormatter()
        protected set

    /**
     * 组标题视图提供器
     */
    open var groupTitleProvider: AbstractGroupTitleProvider = NormalGroupTitleProvider()
        protected set

    /**
     * 详情标题视图提供器
     */
    open var detailTitleProvider: AbstractDetailProvider = NormalDetailProvider()
        protected set

    /**
     * EMS 值
     */
    open var emsSize: FormEmsSize = FormEmsSize(5)
        protected set

    /**
     * 内容重力
     */
    open var contentGravity: FormContentGravity = FormContentGravity(Gravity.END, Gravity.START)
        protected set

    /**
     * 排版
     */
    open var typeset: AbstractTypeset = HorizontalTypeset()
        protected set
}