package com.chooongg.formView.style

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.R
import com.chooongg.formView.config.FormConfigImpl
import com.chooongg.formView.config.IFormConfig
import com.chooongg.formView.data.FormSizeInfo
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrResourcesId

abstract class AbstractFormStyle : IFormConfig by FormConfigImpl() {

    private var isInstanceSizeInfo: Boolean = false

    var marginInfo: FormSizeInfo = FormSizeInfo()
        private set

    var paddingInfo: FormSizeInfo = FormSizeInfo()
        private set

    /**
     * 是否装饰空项目
     */
    open fun isDecorateNoneItem(): Boolean = !isIndependentItem

    /**
     * 内容是否与边缘垂直对齐
     */
    open fun isFillVerticalMargin(): Boolean = true

    /**
     * 内容是否与边缘水平对齐
     */
    open fun isFillHorizontalMargin(): Boolean = true

    /**
     * 是否填充垂直Padding
     */
    open fun isFillVerticalPadding(): Boolean = true

    /**
     * 是否填充水平Padding
     */
    open fun isFillHorizontalPadding(): Boolean = true

    open fun onCreateStyle(parent: ViewGroup): ViewGroup? = null

    open fun configStyleAddChildView(styleView: ViewGroup, childView: View) {
        styleView.addView(childView)
    }

    open fun onStyleAttachedToWindow(holder: FormItemViewHolder) = Unit

    open fun onBindStyleBefore(holder: FormItemViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onBindStyle(holder: FormItemViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onBindStyleAfter(holder: FormItemViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onStyleDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onStyleRecycled(holder: FormItemViewHolder) = Unit

    fun createSizeInfo(context: Context) {
        if (isInstanceSizeInfo) return
        isInstanceSizeInfo = true
        marginInfo = onCreateMargin(context)
        paddingInfo = onCreatePadding(context)
    }

    open fun onCreateMargin(context: Context): FormSizeInfo {
        return ContextThemeWrapper(
            context, context.attrResourcesId(R.attr.formMarginInfo, R.style.Form_Size_Margin)
        ).obtainStyledAttributes(R.styleable.FormSizeInfo).use {
            FormSizeInfo(
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formStart, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formTop, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formEnd, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formBottom, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formStartMedium, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formTopMedium, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formEndMedium, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formBottomMedium, 0),
            )
        }
    }

    open fun onCreatePadding(context: Context): FormSizeInfo {
        return ContextThemeWrapper(
            context, context.attrResourcesId(R.attr.formPaddingInfo, R.style.Form_Size_Padding)
        ).obtainStyledAttributes(R.styleable.FormSizeInfo).use {
            FormSizeInfo(
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formStart, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formTop, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formEnd, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formBottom, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formStartMedium, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formTopMedium, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formEndMedium, 0),
                it.getDimensionPixelSize(R.styleable.FormSizeInfo_formBottomMedium, 0),
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractFormStyle) return false
        if (other.javaClass != javaClass) return false
        return equalsConfig(other)
    }

    override fun hashCode(): Int = javaClass.hashCode()
}