package com.chooongg.form.configuration

import androidx.annotation.StyleRes
import com.chooongg.form.FormManager
import com.chooongg.form.enum.FormContentGravity
import com.chooongg.form.enum.FormEmsSize
import com.chooongg.form.formatter.name.AbstractNameFormatter
import com.chooongg.form.provider.AbstractFormProviderGroupTitle
import com.chooongg.form.typeset.AbstractTypeset
import kotlin.reflect.KClass

abstract class AbstractFormConfiguration {

    @StyleRes
    open val shapeAppearanceResId: Int? = null

    /**
     * 名称格式化程序
     */
    abstract val _nameFormatter: AbstractNameFormatter?

    /**
     * 组标题视图提供器
     */
    abstract val _groupTitleProvider: KClass<out AbstractFormProviderGroupTitle>?

//    /**
//     * 详情标题视图提供器
//     */
//    abstract val _detailTitleProvider: AbstractDetailProvider?

    /**
     * EMS 值
     */
    abstract val _emsSize: FormEmsSize?

    /**
     * 内容重力
     */
    abstract val _contentGravity: FormContentGravity?

    /**
     * 排版
     */
    abstract val _typeset: AbstractTypeset?


    val nameFormatter: AbstractNameFormatter
        get() = _nameFormatter ?: FormManager.globalConfig.nameFormatter

    val groupTitleProvider: KClass<out AbstractFormProviderGroupTitle>
        get() = _groupTitleProvider ?: FormManager.globalConfig.groupTitleProvider

//    val detailTitleProvider: AbstractDetailProvider
//        get() = _detailTitleProvider ?: FormManager.globalConfig.detailTitleProvider

    val emsSize: FormEmsSize
        get() = _emsSize ?: FormManager.globalConfig.emsSize

    val contentGravity: FormContentGravity
        get() = _contentGravity ?: FormManager.globalConfig.contentGravity

    val typeset: AbstractTypeset
        get() = _typeset ?: FormManager.globalConfig.typeset

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractFormConfiguration) return false

        if (shapeAppearanceResId != other.shapeAppearanceResId) return false
        if (_nameFormatter != other._nameFormatter) return false
        if (_groupTitleProvider != other._groupTitleProvider) return false
        if (_emsSize != other._emsSize) return false
        if (_contentGravity != other._contentGravity) return false
        return _typeset == other._typeset
    }

    override fun hashCode(): Int = javaClass.hashCode()
}