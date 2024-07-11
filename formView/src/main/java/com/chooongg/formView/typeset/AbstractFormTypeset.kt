package com.chooongg.formView.typeset

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.itemDelegation.IFormEms
import com.chooongg.formView.itemDelegation.IFormMenu
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView

abstract class AbstractFormTypeset : FormTextAppearanceHelper {

    abstract var emsMode: FormEmsMode

    abstract fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup

    open fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView)
    }

    open fun onTypesetAttachedToWindow(holder: FormItemViewHolder) = Unit

    open fun onBindTypeset(holder: FormItemViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onBindTypeset(
        holder: FormItemViewHolder, item: AbstractFormItem<*>, payloads: MutableList<Any>
    ) = onBindTypeset(holder, item)

    open fun onTypesetDetachedFromWindow(holder: FormItemViewHolder) = Unit

    open fun onTypesetRecycled(holder: FormItemViewHolder) = Unit

    protected fun configNameView(
        holder: FormItemViewHolder, item: AbstractFormItem<*>, nameView: TextView
    ) {
        val size = (item as? IFormEms)?.emsSize ?: holder.style.config.emsSize
        val emsMode = (item as? IFormEms)?.emsMode ?: emsMode
        val isMultiColumn = false
        when (if (isMultiColumn) emsMode.multiColumnMode else emsMode.mode) {
            FormEmsMode.MIN -> {
                nameView.minEms = if (isMultiColumn) size.multiColumnSize else size.size
                nameView.maxWidth = Int.MAX_VALUE
            }

            FormEmsMode.MAX -> {
                nameView.minWidth = Int.MAX_VALUE
                nameView.maxEms = if (isMultiColumn) size.multiColumnSize else size.size
            }

            FormEmsMode.FIXED -> {
                nameView.setEms(if (isMultiColumn) size.multiColumnSize else size.size)
            }

            else -> {
                nameView.minWidth = 0
                nameView.maxWidth = Int.MAX_VALUE
            }
        }
    }

    protected fun configMenuView(
        holder: FormItemViewHolder, item: AbstractFormItem<*>, menuView: FormMenuView
    ) {
        if (item !is IFormMenu) {
            menuView.gone()
            return
        }
        menuView.setMenu(item, item.isEnabled ?: false) { view, menu ->
            val isIntercept = item.onMenuItemClickListener?.invoke(holder.itemView, view, menu)
            if (isIntercept != true) {
                (holder.bindingAdapter as? AbstractFormPart<*>)?._recyclerView?.onMenuClickListener?.invoke(
                    holder.itemView, view, menu, item
                )
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractFormTypeset) return false
        if (other.javaClass != javaClass) return false
        if (other.emsMode != emsMode) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()
}