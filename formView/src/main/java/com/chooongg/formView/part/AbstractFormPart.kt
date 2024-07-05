package com.chooongg.formView.part

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormAdapter
import com.chooongg.formView.FormManager
import com.chooongg.formView.data.AbstractFormId
import com.chooongg.formView.data.IFormPart
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class AbstractFormPart<DATA>(
    val style: AbstractFormStyle, var data: DATA, isEnabled: Boolean
) : RecyclerView.Adapter<FormItemViewHolder>() where DATA : IFormPart, DATA : AbstractFormId {

    var isEnabled = isEnabled
        internal set(value) {
            if (field != value) {
                field = value
                update()
            }
        }

    var partScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        internal set

    internal var _adapter: FormAdapter? = null

    val adapter: FormAdapter get() = _adapter!!

    protected val differ = AsyncListDiffer(object : ListUpdateCallback {
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

//    init {
//        update()
//    }

    fun update() {
        executeUpdate {
            differ.currentList.forEachIndexed { index, item ->
                if (item.lastEnabled != item.enabled) {
                    notifyItemChanged(index)
                } else {
                    notifyItemChanged(index, FormManager.FLAG_PAYLOAD_UPDATE_CONTENT)
                }
            }
        }
    }

    protected abstract fun executeUpdate(commitCallback: Runnable)

    abstract operator fun get(field: String): BaseForm<*>?

    abstract operator fun contains(field: String): Boolean

    abstract operator fun contains(item: BaseForm<*>): Boolean

    override fun getItemCount() = differ.currentList.size

    override fun getItemViewType(position: Int) =
        adapter.getItemViewType4Pool(this, style, differ.currentList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormItemViewHolder {
        val style = adapter.getStyle(viewType).apply { createSizeInfo(parent.context) }
        val typeset = adapter.getTypeset(viewType)
        val itemProvider = adapter.getItemProvider(viewType)
        val styleView = style.onCreateStyle(parent)
        val typesetView = typeset.onCreateTypeset(style, styleView ?: parent)
        val itemView = itemProvider.onCreateViewHolder(style, typesetView ?: styleView ?: parent)
        if (styleView != null) {
            if (typesetView != null) style.configStyleAddChildView(styleView, typesetView)
            else style.configStyleAddChildView(styleView, itemView)
        } else if (typesetView != null) typeset.configTypesetAddChildView(typesetView, itemView)
        return FormItemViewHolder(style, typeset, styleView ?: typesetView ?: itemView)
    }

    override fun onViewAttachedToWindow(holder: FormItemViewHolder) {
        holder.style.onStyleAttachedToWindow(holder)
        holder.typeset.onTypesetAttachedToWindow(holder)
        adapter.getItemProvider(holder.itemViewType).onViewAttachedToWindow(holder)
    }

    override fun onBindViewHolder(holder: FormItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.style.onBindStyle(holder, item)
        holder.typeset.onBindTypeset(holder, item)
        adapter.getItemProvider(holder.itemViewType).onBindViewHolder(holder, item, isEnabled)
    }

    override fun onBindViewHolder(
        holder: FormItemViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onViewDetachedFromWindow(holder: FormItemViewHolder) {
        holder.style.onStyleDetachedFromWindow(holder)
        holder.typeset.onTypesetDetachedFromWindow(holder)
        adapter.getItemProvider(holder.itemViewType).onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: FormItemViewHolder) {
        holder.clear()
        holder.style.onStyleRecycled(holder)
        holder.typeset.onTypesetRecycled(holder)
        adapter.getItemProvider(holder.itemViewType).onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        if (recyclerView.adapter is FormAdapter) {
            _adapter = recyclerView.adapter as FormAdapter
            update()
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        partScope.cancel()
        partScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        _adapter = null
    }
}