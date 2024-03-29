package com.chooongg.form.typeset

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chooongg.form.enum.FormContentGravity
import com.chooongg.form.enum.FormEmsMode
import com.chooongg.form.enum.FormEmsSize
import com.chooongg.form.helper.FormTextAppearanceHelper
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.item.BaseForm
import com.chooongg.form.part.AbstractPart
import com.chooongg.form.style.AbstractStyle

/**
 * 排版
 */
abstract class AbstractTypeset : FormTextAppearanceHelper {

    /**
     * Ems模式
     */
    abstract var emsMode: FormEmsMode

    /**
     * Ems值
     */
    open var emsSize: FormEmsSize? = null

    /**
     * 内容重力
     */
    open var contentGravity: FormContentGravity? = null

    abstract fun onCreateViewHolder(style: AbstractStyle, parent: ViewGroup): ViewGroup?

    protected abstract fun addView(style: AbstractStyle, parent: ViewGroup, child: View)

    open fun onViewAttachedToWindow(holder: FormViewHolder) = Unit

    abstract fun onBindViewHolder(
        holder: FormViewHolder,
        item: BaseForm<*>,
        layout: ViewGroup,
        adapterEnabled: Boolean
    )

    open fun onViewDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onViewRecycled(holder: FormViewHolder) = Unit

    fun executeAddView(style: AbstractStyle, parent: ViewGroup, child: View) =
        addView(style, parent, child)

    fun setNameViewEms(holder: FormViewHolder, textView: TextView) {
        val part = holder.bindingAdapter as? AbstractPart<*>
        val size = emsSize ?: holder.style.config.emsSize
        val isMultiColumn = /*(part?.columnCount ?: 1) > 1*/ false // TODO
        when (if (isMultiColumn) emsMode.multiColumnMode else emsMode.mode) {
            FormEmsMode.MIN -> {
                textView.minEms = if (isMultiColumn) size.multiColumnSize else size.size
                textView.maxWidth = Int.MAX_VALUE
            }

            FormEmsMode.MAX -> {
                textView.minWidth = Int.MAX_VALUE
                textView.maxEms = if (isMultiColumn) size.multiColumnSize else size.size
            }

            FormEmsMode.FIXED -> {
                textView.setEms(if (isMultiColumn) size.multiColumnSize else size.size)
            }

            else -> {
                textView.minWidth = 0
                textView.maxWidth = Int.MAX_VALUE
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractTypeset) return false
        if (javaClass != other.javaClass) return false
        if (emsMode != other.emsMode) return false
        if (emsSize != other.emsSize) return false
        return contentGravity == other.contentGravity
    }

    override fun hashCode(): Int = javaClass.hashCode()
}