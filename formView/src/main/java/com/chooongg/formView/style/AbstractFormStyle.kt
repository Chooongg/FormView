package com.chooongg.formView.style

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.R
import com.chooongg.formView.config.FormConfigImpl
import com.chooongg.formView.config.IFormConfig
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.data.FormSizeInfo
import com.chooongg.formView.helper.IFormItemAttributeHelper
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrResourcesId
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.ShapeAppearanceModel

/**
 * 样式
 */
abstract class AbstractFormStyle : IFormConfig by FormConfigImpl(), IFormItemAttributeHelper {

    private var isInstanceSizeInfo: Boolean = false

    var margin: FormSizeInfo = FormSizeInfo()
        private set

    var padding: FormSizeInfo = FormSizeInfo()
        private set

    /**
     * 是否是独立的Item
     */
    open var isIndependentItem: Boolean = false

    /**
     * 形状外观模型
     */
    lateinit var shapeAppearanceModel: ShapeAppearanceModel

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

    open fun onStyleAttachedToWindow(holder: FormViewHolder) = Unit

    open fun onBindStyleLayoutPadding(holder: FormViewHolder, item: AbstractFormItem<*>) {
        val start = when (item.boundary.start) {
            FormBoundary.NONE -> 0
            else -> if (item.fillEdgesPadding && isFillHorizontalPadding()) {
                padding.start - padding.startMedium
            } else 0
        }
        val end = when (item.boundary.end) {
            FormBoundary.NONE -> 0
            else -> if (item.fillEdgesPadding && isFillHorizontalPadding()) {
                padding.end - padding.endMedium
            } else 0
        }
        val top = when (item.boundary.top) {
            FormBoundary.NONE -> 0
            else -> if (item.fillEdgesPadding && isFillVerticalPadding()) {
                padding.top - padding.topMedium
            } else 0
        }
        val bottom = when (item.boundary.bottom) {
            FormBoundary.NONE -> 0
            else -> if (item.fillEdgesPadding && isFillVerticalPadding()) {
                padding.bottom - padding.bottomMedium
            } else 0
        }
        holder.itemView.setPaddingRelative(start, top, end, bottom)
    }

    open fun onBindStyleBefore(holder: FormViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onBindStyle(holder: FormViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onBindStyleAfter(holder: FormViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onStyleDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onStyleRecycled(holder: FormViewHolder) = Unit

    fun createInfo(context: Context) {
        if (isInstanceSizeInfo) return
        isInstanceSizeInfo = true
        val resId = context.attrResourcesId(
            R.attr.formShapeAppearanceCorner, context.attrResourcesId(
                com.google.android.material.R.attr.shapeAppearanceCornerMedium, 0
            )
        )
        shapeAppearanceModel = ShapeAppearanceModel.builder(context, resId, 0).build()
        margin = onCreateMargin(context)
        padding = onCreatePadding(context)
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

    protected fun getShapeAppearanceModel(holder: FormViewHolder, item: AbstractFormItem<*>) =
        shapeAppearanceModel.toBuilder().apply {
            if (getBindingPart(holder)?._recyclerView?.layoutManager?.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                if (item.boundary.top == 0 || item.boundary.start == 0) {
                    setTopRightCornerSize(AbsoluteCornerSize(0f))
                }
                if (item.boundary.top == 0 || item.boundary.end == 0) {
                    setTopLeftCornerSize(AbsoluteCornerSize(0f))
                }
                if (item.boundary.bottom == 0 || item.boundary.start == 0) {
                    setBottomRightCornerSize(AbsoluteCornerSize(0f))
                }
                if (item.boundary.bottom == 0 || item.boundary.end == 0) {
                    setBottomLeftCornerSize(AbsoluteCornerSize(0f))
                }
            } else {
                if (item.boundary.top == 0 || item.boundary.start == 0) {
                    setTopLeftCornerSize(AbsoluteCornerSize(0f))
                }
                if (item.boundary.top == 0 || item.boundary.end == 0) {
                    setTopRightCornerSize(AbsoluteCornerSize(0f))
                }
                if (item.boundary.bottom == 0 || item.boundary.start == 0) {
                    setBottomLeftCornerSize(AbsoluteCornerSize(0f))
                }
                if (item.boundary.bottom == 0 || item.boundary.end == 0) {
                    setBottomRightCornerSize(AbsoluteCornerSize(0f))
                }
            }
        }.build()

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractFormStyle) return false
        if (other.javaClass != javaClass) return false
        if (other.isIndependentItem != isIndependentItem) return false
        return equalsConfig(other)
    }

    override fun hashCode(): Int = javaClass.hashCode()
}