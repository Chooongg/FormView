package com.chooongg.formView.style

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.R
import com.chooongg.formView.config.AbstractFormConfig
import com.chooongg.formView.data.FormSizeInfo
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.ktx.attrResourcesId

abstract class AbstractFormStyle(val config: AbstractFormConfig) {

    private var isInstanceSizeInfo: Boolean = false

    var marginInfo: FormSizeInfo = FormSizeInfo()
        private set

    var paddingInfo: FormSizeInfo = FormSizeInfo()
        private set

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

    open fun onCreateStyle(parent: ViewGroup): ViewGroup? = null

    open fun configStyleAddChildView(styleView: ViewGroup, childView: View) {
        styleView.addView(childView)
    }

    open fun onStyleAttachedToWindow(holder: FormItemViewHolder) = Unit

    open fun onBindStyle(holder: FormItemViewHolder, item: BaseForm<*>) = Unit

    open fun onBindStyle(
        holder: FormItemViewHolder, item: BaseForm<*>, payloads: MutableList<Any>
    ) = onBindStyle(holder, item)

    open fun onStyleDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onStyleRecycled(holder: FormItemViewHolder) = Unit
    override fun equals(other: Any?): Boolean {
        if (other !is AbstractFormStyle) return false
        if (other.javaClass != javaClass) return false
        return other.config == config
    }

    override fun hashCode(): Int = javaClass.hashCode()
}