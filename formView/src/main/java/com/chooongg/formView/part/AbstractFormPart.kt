package com.chooongg.formView.part

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormAdapter
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormView
import com.chooongg.formView.data.AbstractFormId
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.data.IFormName
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.item.InternalFormNone
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.ktx.resDimensionPixelSize
import com.chooongg.ktx.showToast
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class AbstractFormPart<DATA>(
    val style: AbstractFormStyle, var data: DATA, isEnabled: Boolean
) : RecyclerView.Adapter<FormItemViewHolder>() where DATA : IFormName, DATA : AbstractFormId {

    private val spanCount = 27720

    var isEnabled = isEnabled
        internal set(value) {
            if (field != value) {
                field = value
                update()
            }
        }

    var partScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        internal set

    internal var _recyclerView: FormView? = null

    internal var _adapter: FormAdapter? = null

    val adapter: FormAdapter get() = _adapter!!

    internal val columnCount: Int get() = _recyclerView?.layoutManager?.formColumn ?: 1

    protected val differ = AsyncListDiffer(object : ListUpdateCallback {
        override fun onChanged(p: Int, count: Int, payload: Any?) = Unit
        override fun onRemoved(p: Int, count: Int) = notifyItemRangeRemoved(p, count)
        override fun onInserted(p: Int, count: Int) = notifyItemRangeInserted(p, count)
        override fun onMoved(from: Int, to: Int) = notifyItemMoved(from, to)
    }, AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<BaseForm<*>>() {
        override fun areContentsTheSame(oldItem: BaseForm<*>, newItem: BaseForm<*>) = true
        override fun areItemsTheSame(oldItem: BaseForm<*>, newItem: BaseForm<*>) =
            oldItem.id == newItem.id && oldItem.typeset == newItem.typeset
    }).build())

    fun update() {
        showToast("列数${columnCount}")
        if (_adapter == null) return
        val groups = getOriginalItemList()
        val ignoreListCount = getIgnoreListCount()
        val tempList = ArrayList<ArrayList<BaseForm<*>>>()
        groups.forEach { group ->
            val tempGroup = ArrayList<BaseForm<*>>()
            group.forEach { item ->
                item.enabled = item.isEnable(isEnabled)
                item.resetInternalData()
//                item.initialize()
                if (item.isVisible(isEnabled)) {
                    when (item) {
                        else -> tempGroup.add(item)
                    }
                }
            }
            while (tempGroup.firstOrNull()?.showAtEdge == false) {
                tempGroup.removeFirst()
            }
            while (tempGroup.lastOrNull()?.showAtEdge == false) {
                tempGroup.removeLast()
            }
            tempList.add(tempGroup)
        }
        val tempList2 = ArrayList<List<BaseForm<*>>>()
        tempList.forEach { group ->
            val tempGroup = ArrayList<BaseForm<*>>()
            var spanIndex = 0
            group.forEachIndexed { position, item ->
                item.spanIndex = spanIndex
                item.spanSize = when {
                    item.loneLine -> {
                        spanIndex = 0
                        item.spanIndex = 0
                        spanCount
                    }

                    else -> spanCount / columnCount
                }
                if (position > 0 && item.spanIndex == 0) {
                    val lastItem = group[position - 1]
                    if (lastItem.spanIndex + lastItem.spanSize < spanCount) {
                        if (lastItem.autoFill) {
                            lastItem.spanSize = spanCount - lastItem.spanIndex
                        } else if (style.isDecorateNoneItem()) {
                            val noneIndex = lastItem.spanIndex + lastItem.spanSize
                            tempGroup.add(InternalFormNone(noneIndex, spanCount - noneIndex))
                        }
                    }
                }
                spanIndex = if (spanIndex + item.spanSize < spanCount) {
                    spanIndex + item.spanSize
                } else 0
                tempGroup.add(item)
                if (position == group.lastIndex && item.spanIndex + item.spanSize < spanCount) {
                    if (item.autoFill) {
                        item.spanSize = spanCount - item.spanIndex
                    } else if (style.isDecorateNoneItem()) {
                        val noneIndex = item.spanIndex + item.spanSize
                        tempGroup.add(InternalFormNone(noneIndex, spanCount - noneIndex))
                    }
                }
            }
            tempList2.add(tempGroup)
        }
        var localPosition = 0
        tempList2.forEachIndexed { index, group ->
            group.forEachIndexed { position, item ->
                item.groupCount = tempList2.size - ignoreListCount
                item.groupIndex = index
                item.countInGroup = group.size
                item.positionInGroup = position
                item.localPosition = localPosition
                localPosition++
            }
        }
        differ.submitList(ArrayList<BaseForm<*>>().apply { tempList2.forEach { addAll(it) } }) {
            calculateBoundary()
            differ.currentList.forEachIndexed { index, item ->
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

    private fun calculateBoundary() {
        differ.currentList.forEachIndexed { index, item ->
            // Start
            val start = if (item.spanIndex == 0) {
                FormBoundary.GLOBAL
            } else if (style.isIndependentItem) {
                FormBoundary.MIDDLE
            } else {
                FormBoundary.NONE
            }
            // End
            val isGroupLast = item.positionInGroup == item.countInGroup - 1
            val end = if ((item.spanIndex + item.spanSize >= spanCount || isGroupLast)) {
                FormBoundary.GLOBAL
            } else if (style.isIndependentItem) {
                FormBoundary.MIDDLE
            } else {
                FormBoundary.NONE
            }
            // Top
            val top = if (item.positionInGroup == 0) {
                FormBoundary.MIDDLE
            } else if (item.spanIndex == 0) {
                FormBoundary.NONE
            } else {
                var beginIndex = index - 1
                var beginItem = get(beginIndex)
                while (beginIndex < differ.currentList.lastIndex && beginItem.spanIndex != 0 && item.positionInGroup != 0) {
                    beginIndex--
                    beginItem = get(beginIndex)
                }
                beginItem.boundary.top
            }
            item.boundary = FormBoundary(start, top, end, FormBoundary.NONE)
        }
        for (index in differ.currentList.lastIndex downTo 0) {
            val item = get(index)
            // Bottom
            val bottom = if (item.countInGroup - 1 - item.positionInGroup == 0) {
                FormBoundary.MIDDLE
            } else if (item.spanIndex + item.spanIndex >= spanCount) {
                FormBoundary.NONE
            } else {
                var lastIndex = index
                var lastItem = get(lastIndex)
                while (lastIndex < differ.currentList.lastIndex && get(lastIndex + 1).spanIndex != 0 && lastItem.countInGroup - 1 - lastItem.positionInGroup != 0) {
                    lastIndex++
                    lastItem = get(lastIndex)
                }
                lastItem.boundary.bottom
            }
            item.boundary = item.boundary.update(bottom = bottom)
        }
    }

    protected abstract fun getOriginalItemList(): List<List<BaseForm<*>>>

    protected open fun getIgnoreListCount(): Int = 0

    operator fun get(position: Int): BaseForm<*> = differ.currentList[position]

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
        return FormItemViewHolder(style, typeset, styleView ?: typesetView ?: itemView).apply {
            this.itemView.layoutParams = GridLayoutManager.LayoutParams(-1, -2)
        }
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
        val item = differ.currentList[position]
        holder.style.onBindStyle(holder, item)
        holder.typeset.onBindTypeset(holder, item)
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
        } else _adapter = null
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        partScope.cancel()
        partScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        _adapter = null
    }
}