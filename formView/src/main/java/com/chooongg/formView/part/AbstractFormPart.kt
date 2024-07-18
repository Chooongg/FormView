package com.chooongg.formView.part

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormAdapter
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormView
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.data.IFormPart
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.FormPlaceHolder
import com.chooongg.formView.itemProvider.FormPlaceHolderProvider
import com.chooongg.formView.style.AbstractFormStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

abstract class AbstractFormPart<DATA : IFormPart>(
    val style: AbstractFormStyle, var data: DATA, isEnabled: Boolean
) : RecyclerView.Adapter<FormViewHolder>() {

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

    protected val differ = AsyncListDiffer(object : ListUpdateCallback {
        override fun onChanged(p: Int, count: Int, payload: Any?) = Unit
        override fun onRemoved(p: Int, count: Int) = notifyItemRangeRemoved(p, count)
        override fun onInserted(p: Int, count: Int) = notifyItemRangeInserted(p, count)
        override fun onMoved(from: Int, to: Int) = notifyItemMoved(from, to)
    }, AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<AbstractFormItem<*>>() {
        override fun areContentsTheSame(
            oldItem: AbstractFormItem<*>, newItem: AbstractFormItem<*>
        ) = true

        override fun areItemsTheSame(oldItem: AbstractFormItem<*>, newItem: AbstractFormItem<*>) =
            oldItem.id == newItem.id && oldItem.typeset == newItem.typeset
    }).build())

    private var updateJob: Job? = null

    fun update() {
        updateJob?.cancel()
        updateJob = partScope.launch {
            if (_adapter == null) {
                differ.submitList(null)
                return@launch
            }
            val adapterColumnCount = _adapter?.columnCount ?: -1
            if (adapterColumnCount <= 0) {
                differ.submitList(null)
                return@launch
            }
            val columnCount = data.partConfig.column?.obtain(adapterColumnCount)
                ?: adapter.data.dataConfig.column?.obtain(adapterColumnCount) ?: adapterColumnCount
            if (columnCount < 0) {
                differ.submitList(null)
                return@launch
            }
            val groups = getOriginalItemList()
            val ignoreListCount = getIgnoreListCount()
            val tempList = ArrayList<ArrayList<AbstractFormItem<*>>>()
            groups.forEach { group ->
                val tempGroup = ArrayList<AbstractFormItem<*>>()
                group.forEach { item ->
                    item.resetInternalData()
                    item.isEnabled = item.isEnable(isEnabled)
                    item.isMenuVisible = item.isMenuVisible(isEnabled)
                    item.isMenuEnabled = item.isMenuEnable(isEnabled)
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
            val tempList2 = ArrayList<List<AbstractFormItem<*>>>()
            tempList.forEach { group ->
                val tempGroup = ArrayList<AbstractFormItem<*>>()
                var columnIndex = 0
                group.forEachIndexed { position, item ->
                    item.columnCount = columnCount
                    item.columnSize = when {
                        item.loneLine -> columnCount
                        item.columnProvider != null -> {
                            max(1, min(columnCount, item.columnProvider!!.invoke(columnCount)))
                        }

                        else -> 1
                    }
                    item.columnIndex = when {
                        item.positionInGroup == 0 -> 0
                        item.loneLine -> 0
                        item.newLine -> 0
                        columnIndex + item.columnSize <= columnCount -> columnIndex
                        else -> 0
                    }
                    columnIndex = if (item.columnIndex + item.columnSize < columnCount) {
                        item.columnIndex + item.columnSize
                    } else 0
                    if (position > 0 && item.columnIndex == 0) {
                        val lastItem = group[position - 1]
                        if (lastItem.columnIndex + lastItem.columnSize < columnCount) {
                            if (lastItem.autoFill) {
                                lastItem.columnSize = columnCount - lastItem.columnIndex
                            } else {
                                val noneIndex = lastItem.columnIndex + lastItem.columnSize
                                tempGroup.add(
                                    FormPlaceHolder(columnCount, noneIndex, columnCount - noneIndex)
                                )
                            }
                        }
                    }
                    tempGroup.add(item)
                }
                if (tempGroup.isNotEmpty()) {
                    val lastItem = tempGroup.last()
                    if (lastItem.columnIndex + lastItem.columnSize < columnCount) {
                        if (lastItem.autoFill) {
                            lastItem.columnSize = columnCount - lastItem.columnIndex
                        } else {
                            val noneIndex = lastItem.columnIndex + lastItem.columnSize
                            tempGroup.add(
                                FormPlaceHolder(columnCount, noneIndex, columnCount - noneIndex)
                            )
                        }
                    }
                    tempList2.add(tempGroup)
                }
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
            differ.submitList(ArrayList<AbstractFormItem<*>>().apply { tempList2.forEach { addAll(it) } }) {
                calculateBoundary()
                differ.currentList.forEachIndexed { index, item ->
                    if (item.lastEnabled != item.isEnabled || item.lastMenuVisible != item.isMenuVisible) {
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
    }

    private fun calculateBoundary() {
        val partIndex = _adapter?.visiblePartIndexOf(this) ?: -1
        val partCount = _adapter?.visiblePartCount() ?: -1
        differ.currentList.forEachIndexed { index, item ->
            // Start
            val start = if (item.columnIndex == 0) {
                FormBoundary.GLOBAL
            } else if (style.isIndependentItem) {
                FormBoundary.MIDDLE
            } else {
                FormBoundary.NONE
            }
            // End
            val isGroupLast = item.positionInGroup == item.countInGroup - 1
            val end = if (item.columnIndex + item.columnSize >= item.columnCount) {
                FormBoundary.GLOBAL
            } else if (isGroupLast || style.isIndependentItem) {
                FormBoundary.MIDDLE
            } else {
                FormBoundary.NONE
            }
            // Top
            val top = if (partIndex == 0 && item.positionInGroup == 0) {
                FormBoundary.GLOBAL
            } else if (item.positionInGroup == 0) {
                FormBoundary.MIDDLE
            } else if (item.columnIndex == 0) {
                FormBoundary.NONE
            } else {
                var beginIndex = index - 1
                var beginItem = get(beginIndex)
                while (beginIndex < differ.currentList.lastIndex && beginItem.columnIndex != 0 && item.positionInGroup != 0) {
                    beginIndex--
                    beginItem = get(beginIndex)
                }
                beginItem.boundary.top
            }
            item.boundary = FormBoundary(
                start,
                if (style.isIndependentItem && top == FormBoundary.NONE) FormBoundary.MIDDLE else top,
                end,
                FormBoundary.NONE
            )
        }
        for (index in differ.currentList.lastIndex downTo 0) {
            val item = get(index)
            // Bottom
            val bottom =
                if (partIndex == partCount - 1 && item.countInGroup - 1 - item.positionInGroup == 0) {
                    FormBoundary.GLOBAL
                } else if (item.countInGroup - 1 - item.positionInGroup == 0) {
                    FormBoundary.MIDDLE
                } else if (item.columnIndex + item.columnSize >= item.columnCount) {
                    if (style.isIndependentItem) FormBoundary.MIDDLE else FormBoundary.NONE
                } else {
                    var lastIndex = index
                    var lastItem = get(lastIndex)
                    while (lastIndex < differ.currentList.lastIndex && get(lastIndex + 1).columnIndex != 0 && lastItem.countInGroup - 1 - lastItem.positionInGroup != 0) {
                        lastIndex++
                        lastItem = get(lastIndex)
                    }
                    lastItem.boundary.bottom
                }
            item.boundary = item.boundary.update(
                bottom = if (style.isIndependentItem && bottom == FormBoundary.NONE) FormBoundary.MIDDLE else bottom
            )
        }
    }

    protected abstract fun getOriginalItemList(): List<List<AbstractFormItem<*>>>

    protected open fun getIgnoreListCount(): Int = 0

    operator fun get(position: Int): AbstractFormItem<*> = differ.currentList[position]

    abstract operator fun get(field: String): AbstractFormItem<*>?

    abstract operator fun contains(field: String): Boolean

    abstract operator fun contains(item: AbstractFormItem<*>): Boolean

    override fun getItemCount() = differ.currentList.size

    override fun getItemViewType(position: Int) =
        adapter.getItemViewType4Pool(this, style, differ.currentList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val style = adapter.getStyle(viewType).apply { createInfo(parent.context) }
        val typeset = adapter.getTypeset(viewType)
        val itemProvider = adapter.getItemProvider(viewType)
        val styleView =
            if (itemProvider !is FormPlaceHolderProvider || style.isDecorateNoneItem()) {
                style.onCreateStyle(parent)
            } else null
        val typesetView = typeset.onCreateTypeset(style, styleView ?: parent)
        val itemView = itemProvider.onCreateViewHolder(this, style, typesetView)
        if (styleView != null) {
            style.configStyleAddChildView(styleView, typesetView)
        } else typeset.configTypesetAddChildView(typesetView, itemView)
        return FormViewHolder(style, typeset, styleView ?: typesetView)
    }

    override fun onViewAttachedToWindow(holder: FormViewHolder) {
        holder.style.onStyleAttachedToWindow(holder)
        holder.typeset.onTypesetAttachedToWindow(holder)
        adapter.getItemProvider(holder.itemViewType).onViewAttachedToWindow(holder)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.tag = holder.itemView.z
        item.globalPosition = holder.absoluteAdapterPosition
        item.localPosition = holder.bindingAdapterPosition
        if (item !is FormPlaceHolder || holder.style.isDecorateNoneItem()) {
            holder.style.onBindStyleLayoutPadding(holder, item)
            holder.style.onBindStyleBefore(holder, item)
            holder.style.onBindStyle(holder, item)
            holder.style.onBindStyleAfter(holder, item)
        }
        holder.typeset.onBindTypeset(holder, item)
        adapter.getItemProvider(holder.itemViewType).apply {
            onBindViewHolder(holder, item)
            onBindViewHolderForeground(holder, item)
            onBindViewHolderClick(holder, this@AbstractFormPart, item)
        }
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        val item = differ.currentList[position]
        item.globalPosition = holder.absoluteAdapterPosition
        item.localPosition = holder.bindingAdapterPosition
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }
        payloads.forEach {
            when (it) {
                FormManager.FLAG_PAYLOAD_UPDATE_BOUNDARY -> {
                    if (item !is FormPlaceHolder || holder.style.isDecorateNoneItem()) {
                        holder.style.onBindStyleLayoutPadding(holder, item)
                        holder.style.onBindStyleBefore(holder, item)
                        holder.style.onBindStyle(holder, item)
                        holder.style.onBindStyleAfter(holder, item)
                    }
                }

                FormManager.FLAG_PAYLOAD_UPDATE_CONTENT -> {
                    holder.typeset.onBindTypeset(holder, item, payloads)
                    adapter.getItemProvider(holder.itemViewType).apply {
                        onBindViewHolder(holder, item)
                        onBindViewHolderForeground(holder, item)
                        onBindViewHolderClick(holder, this@AbstractFormPart, item)
                    }
                }

                else -> adapter.getItemProvider(holder.itemViewType)
                    .onBindViewHolderOther(holder, item, it)
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: FormViewHolder) {
        holder.style.onStyleDetachedFromWindow(holder)
        holder.typeset.onTypesetDetachedFromWindow(holder)
        adapter.getItemProvider(holder.itemViewType).onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: FormViewHolder) {
        holder.clear()
        holder.style.onStyleRecycled(holder)
        holder.typeset.onTypesetRecycled(holder)
        adapter.getItemProvider(holder.itemViewType).onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        _recyclerView = recyclerView as? FormView
        _adapter = if (recyclerView.adapter is FormAdapter) {
            recyclerView.adapter as FormAdapter
        } else null
        update()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        _recyclerView = null
        partScope.cancel()
        partScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        _adapter = null
        update()
    }
}