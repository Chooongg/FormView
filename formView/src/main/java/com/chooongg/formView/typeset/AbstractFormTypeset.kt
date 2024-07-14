package com.chooongg.formView.typeset

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chooongg.formView.FormManager
import com.chooongg.formView.helper.IFormItemAttributeHelper
import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView

abstract class AbstractFormTypeset : FormTextAppearanceHelper, IFormItemAttributeHelper {

    abstract val emsMode: FormEmsMode

    open val emsSize: FormEmsSize = FormEmsSize(FormManager.FORM_EMS)

    abstract fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup

    open fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView)
    }

    open fun onTypesetAttachedToWindow(holder: FormViewHolder) = Unit

    open fun onBindTypeset(holder: FormViewHolder, item: AbstractFormItem<*>) = Unit

    open fun onBindTypeset(
        holder: FormViewHolder, item: AbstractFormItem<*>, payloads: MutableList<Any>
    ) = onBindTypeset(holder, item)

    open fun onTypesetDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onTypesetRecycled(holder: FormViewHolder) = Unit

    protected fun configNameView(
        holder: FormViewHolder, item: AbstractFormItem<*>, nameView: TextView
    ) {
        when (emsMode) {
            FormEmsMode.NONE -> {
                nameView.minWidth = 0
                nameView.maxWidth = Int.MAX_VALUE
            }

            FormEmsMode.MIN -> {
                nameView.minEms =
                    obtainEmsSize(holder, item).obtain(item.columnCount, item.columnSize)
                nameView.maxWidth = Int.MAX_VALUE
            }

            FormEmsMode.MAX -> {
                nameView.minWidth = Int.MAX_VALUE
                nameView.maxEms =
                    obtainEmsSize(holder, item).obtain(item.columnCount, item.columnSize)
            }

            FormEmsMode.FIXED -> {
                nameView.setEms(
                    obtainEmsSize(holder, item).obtain(item.columnCount, item.columnSize)
                )
            }
        }
    }

    protected fun configMenuView(
        holder: FormViewHolder, item: AbstractFormItem<*>, menuView: FormMenuView
    ) {
        menuView.setMenu(item, item.isEnabled ?: false) { view, menu ->
            val isIntercept = item.onMenuItemClickListener?.invoke(holder.itemView, view, menu)
            if (isIntercept != true) {
                (holder.bindingAdapter as? AbstractFormPart<*>)?._recyclerView?.onMenuClickListener?.invoke(
                    holder.itemView, view, menu, item
                )
            }
        }
    }

    protected fun obtainEmsSize(holder: FormViewHolder, item: AbstractFormItem<*>): FormEmsSize {
        return item.emsSize ?: holder.style.emsSize ?: getFormAdapter(holder)?.data?.emsSize
        ?: FormManager.globalConfig.emsSize ?: emsSize
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractFormTypeset) return false
        if (other.javaClass != javaClass) return false
        if (other.emsMode != emsMode) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()
}