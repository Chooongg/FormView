package com.chooongg.formView.style

import android.content.Context
import android.graphics.drawable.RippleDrawable
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
import com.chooongg.ktx.attrColorStateList
import com.chooongg.ktx.attrResourcesId
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.MaterialShapeDrawable
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
    protected lateinit var shapeAppearanceModel: ShapeAppearanceModel

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

    open fun onBindStyleBefore(holder: FormViewHolder, item: AbstractFormItem<*>) {
        if (!this::shapeAppearanceModel.isInitialized) {
            val resId = holder.itemView.attrResourcesId(
                R.attr.formShapeAppearanceCorner, holder.itemView.attrResourcesId(
                    com.google.android.material.R.attr.shapeAppearanceCornerMedium, 0
                )
            )
            shapeAppearanceModel =
                ShapeAppearanceModel.builder(holder.itemView.context, resId, 0).build()
        }
    }

    open fun onBindStyle(holder: FormViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onBindStyleAfter(holder: FormViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onStyleDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onStyleRecycled(holder: FormViewHolder) = Unit

    fun createSizeInfo(context: Context) {
        if (isInstanceSizeInfo) return
        isInstanceSizeInfo = true
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

    fun getShapeAppearanceModel(view: View, item: AbstractFormItem<*>) =
        ShapeAppearanceModel.builder().apply {
            if (view.layoutDirection != View.LAYOUT_DIRECTION_RTL) {
                setTopLeftCornerSize(
                    if (item.boundary.top != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.topLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setTopRightCornerSize(
                    if (item.boundary.top != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.topRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomLeftCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.bottomLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomRightCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.bottomRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
            } else {
                setTopLeftCornerSize(
                    if (item.boundary.top != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.topRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setTopRightCornerSize(
                    if (item.boundary.top != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.topLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomLeftCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.bottomRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomRightCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.bottomLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
            }
        }.build()

    open fun getForeground(view: View, item: AbstractFormItem<*>) = RippleDrawable(
        view.attrColorStateList(com.google.android.material.R.attr.colorControlHighlight)!!,
        null,
        MaterialShapeDrawable(getClickShapeAppearanceModel(view, item))
    )

    fun getClickShapeAppearanceModel(view: View, item: AbstractFormItem<*>) =
        ShapeAppearanceModel.builder().apply {
            if (view.layoutDirection != View.LAYOUT_DIRECTION_RTL) {
                setTopLeftCornerSize(
                    when {
                        item.boundary.top != 0 && item.boundary.start != 0 -> shapeAppearanceModel.topLeftCornerSize
                        item.boundary.top == 0 && item.boundary.start == 0 -> shapeAppearanceModel.topLeftCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
                setTopRightCornerSize(
                    when {
                        item.boundary.top != 0 && item.boundary.end != 0 -> shapeAppearanceModel.topRightCornerSize
                        item.boundary.top == 0 && item.boundary.end == 0 -> shapeAppearanceModel.topRightCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
                setBottomLeftCornerSize(
                    when {
                        item.boundary.bottom != 0 && item.boundary.start != 0 -> shapeAppearanceModel.bottomLeftCornerSize
                        item.boundary.bottom == 0 && item.boundary.start == 0 -> shapeAppearanceModel.bottomLeftCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
                setBottomRightCornerSize(
                    when {
                        item.boundary.bottom != 0 && item.boundary.end != 0 -> shapeAppearanceModel.bottomRightCornerSize
                        item.boundary.bottom == 0 && item.boundary.end == 0 -> shapeAppearanceModel.bottomRightCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
            } else {
                setTopLeftCornerSize(
                    when {
                        item.boundary.top != 0 && item.boundary.start != 0 -> shapeAppearanceModel.topRightCornerSize
                        item.boundary.top == 0 && item.boundary.start == 0 -> shapeAppearanceModel.topRightCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
                setTopRightCornerSize(
                    when {
                        item.boundary.top != 0 && item.boundary.end != 0 -> shapeAppearanceModel.topLeftCornerSize
                        item.boundary.top == 0 && item.boundary.end == 0 -> shapeAppearanceModel.topLeftCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
                setBottomLeftCornerSize(
                    when {
                        item.boundary.bottom != 0 && item.boundary.start != 0 -> shapeAppearanceModel.bottomRightCornerSize
                        item.boundary.bottom == 0 && item.boundary.start == 0 -> shapeAppearanceModel.bottomRightCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
                setBottomRightCornerSize(
                    when {
                        item.boundary.bottom != 0 && item.boundary.end != 0 -> shapeAppearanceModel.bottomLeftCornerSize
                        item.boundary.bottom == 0 && item.boundary.end == 0 -> shapeAppearanceModel.bottomLeftCornerSize
                        else -> AbsoluteCornerSize(0f)
                    }
                )
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