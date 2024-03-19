package com.chooongg.form.provider

import android.view.View
import android.view.ViewGroup
import com.chooongg.android.ktx.doOnClick
import com.chooongg.form.helper.FormTextAppearanceHelper
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.item.BaseForm
import com.chooongg.form.part.AbstractPart
import com.chooongg.form.style.AbstractStyle
import kotlinx.coroutines.CoroutineScope

abstract class AbstractFormProvider : FormTextAppearanceHelper {

    abstract fun onCreateViewHolder(style: AbstractStyle, parent: ViewGroup): View

    open fun onViewAttachedToWindow(holder: FormViewHolder) = Unit

    abstract fun onBindViewHolder(
        scope: CoroutineScope,
        holder: FormViewHolder,
        item: BaseForm<*>,
        adapterEnabled: Boolean
    )

    abstract fun onBindContentViewGravity(
        holder: FormViewHolder,
        item: BaseForm<*>,
        isMultiColumn: Boolean
    )

    open fun onBindViewHolderErrorNotify(
        scope: CoroutineScope,
        holder: FormViewHolder,
        item: BaseForm<*>,
        adapterEnabled: Boolean
    ) = Unit

    open fun onBindViewHolderOther(
        scope: CoroutineScope,
        holder: FormViewHolder,
        item: BaseForm<*>,
        adapterEnabled: Boolean,
        payload: Any,
    ) = Unit

    open fun onBindViewItemClick(
        scope: CoroutineScope, holder: FormViewHolder, item: BaseForm<*>, adapterEnabled: Boolean
    ) {
        if (item.isRespondToClickEvents && item.isEnable(adapterEnabled)) {
            holder.itemView.doOnClick {
                (holder.bindingAdapter as? AbstractPart<*>)?.adapter?.onItemClickListener?.invoke(
                    it,
                    item
                )
            }
        } else holder.itemView.setOnClickListener(null)
    }

    open fun onViewDetachedFromWindow(holder: FormViewHolder) = Unit

    open fun onViewRecycled(holder: FormViewHolder) = Unit
}