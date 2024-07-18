package com.chooongg.formView.item

import com.chooongg.formView.FormColorStateListBlock
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormInputProvider
import com.chooongg.formView.listener.FormOnItemClickListener
import com.chooongg.formView.part.AbstractFormPart
import kotlin.reflect.KClass

class FormInput(
    name: Any?, field: String?, content: CharSequence?
) : AbstractFormOptionItem<CharSequence?, CharSequence?>(name, field, content) {

    /**
     * 最小行数
     */
    var minLines: Int = 0

    /**
     * 最大行数
     */
    var maxLines: Int = Int.MAX_VALUE

    /**
     * 最小长度
     */
    var minLength: Int? = null

    /**
     * 最大长度
     */
    var maxLength: Int? = null

    /**
     * 前缀: Int(StringRes), String, CharSequence
     */
    var prefix: Any? = null

    /**
     * 后缀: Int(StringRes), String, CharSequence
     */
    var suffix: Any? = null

    /**
     * 占位: Int(StringRes), String, CharSequence
     */
    var placeholder: Any? = null

    /**
     * 显示清除图标
     */
    var showClearIcon: Boolean = true

    /**
     * 显示计数器
     */
    var showCounter: Boolean = false

    /**
     * 计数器最大长度
     */
    var counterMaxLength: Int? = null

    /**
     * 起始图标
     */
    var startIcon: Any? = null

    /**
     * 起始图标色调 (如果不想使用默认色调, 请实现Block返回null)
     */
    var startIconTint: FormColorStateListBlock? = null

    private var startIconOnClickListener: FormOnItemClickListener? = null

    fun startIconOnClickListener(listener: FormOnItemClickListener?) {
        startIconOnClickListener = listener
    }

    override fun hasOpenOperation(): Boolean = false

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormInputProvider::class
    }
}