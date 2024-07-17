package com.chooongg.formView.itemProvider

import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.RippleDrawable
import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.helper.IFormItemAttributeHelper
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.ktx.attrColorStateList
import com.google.android.material.shape.MaterialShapeDrawable

/**
 * Item提供器
 * @constructor 因为使用反射进行初始化，所以子类必须保留空参构造方法
 */
abstract class AbstractFormItemProvider : FormTextAppearanceHelper, IFormItemAttributeHelper {

    abstract fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View

    open fun onViewAttachedToWindow(holder: FormViewHolder) = Unit

    abstract fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>
    )

    open fun onBindViewHolderOther(
        holder: FormViewHolder, item: AbstractFormItem<*>, payload: Any
    ) = Unit

    open fun onBindViewHolderForeground(
        holder: FormViewHolder, item: AbstractFormItem<*>
    ) {
        holder.itemView.foreground = if (item.isEnabledItemClick && item.isEnabled) {
            if (!item.fillEdgesPadding) {
                RippleDrawable(
                    holder.itemView.attrColorStateList(com.google.android.material.R.attr.colorControlHighlight)!!,
                    null,
                    MaterialShapeDrawable(holder.style.shapeAppearanceModel)
                )
            } else {
                RippleDrawable(
                    holder.itemView.attrColorStateList(com.google.android.material.R.attr.colorControlHighlight)!!,
                    null,
                    if (holder.itemView.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                        InsetDrawable(
                            MaterialShapeDrawable(holder.style.shapeAppearanceModel),
                            holder.itemView.paddingRight,
                            holder.itemView.paddingTop,
                            holder.itemView.paddingLeft,
                            holder.itemView.paddingBottom
                        )
                    } else {
                        InsetDrawable(
                            MaterialShapeDrawable(holder.style.shapeAppearanceModel),
                            holder.itemView.paddingLeft,
                            holder.itemView.paddingTop,
                            holder.itemView.paddingRight,
                            holder.itemView.paddingBottom
                        )
                    }
                )
            }
        } else null
    }

    open fun onBindViewHolderClick(
        holder: FormViewHolder, part: AbstractFormPart<*>, item: AbstractFormItem<*>
    ) {
        if (item.isEnabledItemClick && item.isEnabled) {
            holder.itemView.setOnClickListener {
                val listener = item.onClickListener ?: part._recyclerView?.onItemClickListener
                ?: return@setOnClickListener
                listener.onFormItemClick(holder.itemView, part, item)
            }
        } else holder.itemView.setOnClickListener(null)
    }

    open fun onViewDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onViewRecycled(holder: FormViewHolder) = Unit

    override fun equals(other: Any?) = other?.javaClass == javaClass

    override fun hashCode(): Int = javaClass.hashCode()
}