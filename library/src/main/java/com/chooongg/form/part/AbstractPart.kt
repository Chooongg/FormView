package com.chooongg.form.part

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.FormAdapter
import com.chooongg.form.FormManager
import com.chooongg.form.data.IFormPart
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.item.BaseForm
import com.chooongg.form.style.AbstractStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class AbstractPart<DATA : IFormPart>(
    val adapter: FormAdapter, val style: AbstractStyle, var data: DATA
) : RecyclerView.Adapter<FormViewHolder>() {

    var adapterScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        internal set

    private val asyncDiffer = AsyncListDiffer(object : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) = Unit

        override fun onRemoved(position: Int, count: Int) = notifyItemRangeRemoved(position, count)

        override fun onInserted(position: Int, count: Int) =
            notifyItemRangeInserted(position, count)

        override fun onMoved(fromPosition: Int, toPosition: Int) =
            notifyItemMoved(fromPosition, toPosition)

    }, AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<BaseForm<*>>() {
        override fun areContentsTheSame(oldItem: BaseForm<*>, newItem: BaseForm<*>) = true
        override fun areItemsTheSame(oldItem: BaseForm<*>, newItem: BaseForm<*>) =
            oldItem.id == newItem.id && oldItem.typeset == newItem.typeset
    }).build())

    protected val showItemList: List<BaseForm<*>> get() = asyncDiffer.currentList

    fun update() {
        executeUpdate {
            showItemList.forEachIndexed { index, item ->
                if (item.lastEnabled != item.enabled) {
                    notifyItemChanged(index)
                } else {
                    notifyItemChanged(index, FormManager.FLAG_PAYLOAD_UPDATE_CONTENT)
                    if (item.lastBoundary != item.boundary) {
                        notifyItemChanged(index, FormManager.FLAG_PAYLOAD_UPDATE_BOUNDARY)
                    }
                }
            }
        }
    }

    protected abstract fun executeUpdate(commitCallback: Runnable)

    operator fun get(position: Int): BaseForm<*> = showItemList[position]

    abstract operator fun get(field: String): BaseForm<*>?

    abstract operator fun contains(field: String): Boolean

    abstract operator fun contains(item: BaseForm<*>): Boolean

    fun indexOfShow(item: BaseForm<*>) = showItemList.indexOf(item)

    override fun getItemCount(): Int = showItemList.size

    override fun getItemViewType(position: Int): Int =
        adapter.getItemViewType4Pool(this, style, get(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val style = adapter.getStyle4ItemViewType(viewType)
        style.createSizeInfo(parent.context)
        val typeset = adapter.getTypeset4ItemViewType(viewType)
        val provider = adapter.getProvider4ItemViewType(viewType)
        val styleLayout = style.onCreateViewHolder(parent)?.apply {
            clipChildren = false
            clipToPadding = false
        }
        val typesetLayout = typeset.onCreateViewHolder(style, styleLayout ?: parent)?.apply {
            clipChildren = false
            clipToPadding = false
        }
        val view = provider.onCreateViewHolder(style, typesetLayout ?: styleLayout ?: parent)
        val holder = FormViewHolder(style, styleLayout, typeset, typesetLayout, provider, view)
        holder.itemView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        holder.itemView.textDirection = TextView.TEXT_DIRECTION_LOCALE
        holder.itemView.layoutParams = if (holder.itemView.layoutParams != null) {
            GridLayoutManager.LayoutParams(holder.itemView.layoutParams!!)
        } else GridLayoutManager.LayoutParams(-1, -2)
        return holder
    }

    override fun onViewAttachedToWindow(holder: FormViewHolder) {
        holder.style.onViewAttachedToWindow(holder)
        holder.typeset.onViewAttachedToWindow(holder)
        adapter.getProvider4ItemViewType(holder.itemViewType).onViewAttachedToWindow(holder)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        val item = get(position)
        item.globalPosition = holder.absoluteAdapterPosition
        item.localPosition = holder.bindingAdapterPosition
        holder.style.initializeShapeAppearanceModel(holder)
        holder.style.onBindViewHolderBefore(holder, item, adapter.isEnabled)
        holder.style.onBindViewHolder(holder, item, holder.styleLayout, adapter.isEnabled)
        if (holder.typesetLayout != null) {
            holder.typeset.onBindViewHolder(holder, item, holder.typesetLayout, adapter.isEnabled)
        }

        holder.provider.onBindViewItemClick(adapterScope, holder, item, adapter.isEnabled)
        holder.provider.onBindViewHolder(adapterScope, holder, holder.view, item, adapter.isEnabled)
        holder.style.onBindViewHolderAfter(holder, item, adapter.isEnabled)
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }
        val item = get(position)
        holder.provider.onBindViewItemClick(adapterScope, holder, item, adapter.isEnabled)
        payloads.forEach {
            when (it) {
                FormManager.FLAG_PAYLOAD_UPDATE_CONTENT -> {
                    holder.provider.onBindViewHolder(
                        adapterScope, holder, holder.view, item, adapter.isEnabled
                    )
                }

                FormManager.FLAG_PAYLOAD_UPDATE_BOUNDARY -> {
                    val style = adapter.getStyle4ItemViewType(holder.itemViewType)
                    style.onBindViewHolderBefore(holder, item, adapter.isEnabled)
                    style.onBindViewHolder(holder, item, holder.styleLayout, adapter.isEnabled)
                    style.onBindViewHolderAfter(holder, item, adapter.isEnabled)
                }

                else -> holder.provider.onBindViewHolderOther(
                    adapterScope, holder, holder.view, item, adapter.isEnabled, it
                )
            }
        }
        item.globalPosition = holder.absoluteAdapterPosition
        item.localPosition = holder.bindingAdapterPosition

    }

    override fun onViewDetachedFromWindow(holder: FormViewHolder) {
        holder.style.onViewDetachedFromWindow(holder)
        holder.typeset.onViewDetachedFromWindow(holder)
        adapter.getProvider4ItemViewType(holder.itemViewType).onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: FormViewHolder) {
        holder.style.onViewRecycled(holder)
        holder.typeset.onViewRecycled(holder)
        adapter.getProvider4ItemViewType(holder.itemViewType).onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        update()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        adapterScope.cancel()
        adapterScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        asyncDiffer.submitList(emptyList())
        this.recyclerView = null
    }
}