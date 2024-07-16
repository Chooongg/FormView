package com.chooongg.formView.itemProvider

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.helper.IFormItemAttributeHelper
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle

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

    open fun onBindViewHolderClick(
        holder: FormViewHolder, part: AbstractFormPart<*>, item: AbstractFormItem<*>
    ) {
        if (item.isEnabledItemClick && item.isEnabled) {
            if (item.fillEdgesPadding){
                holder.itemView.foreground = holder.style.getForeground(holder.itemView, item)
            }else{
                val drawable = holder.style.getForeground(holder.itemView, item)
                drawable.bounds = Rect()
                holder.itemView.foreground = drawable
            }
            holder.itemView.setOnClickListener {
                val listener = item.onClickListener ?: part._recyclerView?.onItemClickListener
                ?: return@setOnClickListener
                listener.invoke(holder.itemView, part, item)
            }
        } else {
            holder.itemView.foreground = null
            holder.itemView.setOnClickListener(null)
        }
    }

    open fun onViewDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onViewRecycled(holder: FormViewHolder) = Unit

    override fun equals(other: Any?) = other?.javaClass == javaClass

    override fun hashCode(): Int = javaClass.hashCode()
}