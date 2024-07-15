package com.chooongg.formView

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.data.FormData
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.typeset.AbstractFormTypeset
import kotlin.math.abs
import kotlin.reflect.KClass

class FormAdapter(val data: FormData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            notifyItemRangeChanged(positionStart, itemCount, payload)
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onStateRestorationPolicyChanged() {
            stateRestorationPolicy = data.concatAdapter.stateRestorationPolicy
        }
    }

    val isEnabled get() = data.isEnabled

    internal var columnCount: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun update() {
        data.concatAdapter.adapters.forEach {
            if (it is AbstractFormPart<*>) it.update()
            else it.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = data.concatAdapter.itemCount

    override fun getItemId(position: Int): Long {
        return data.concatAdapter.getItemId(position)
    }

    fun getItem(position: Int): AbstractFormItem<*>? {
        val pair = data.concatAdapter.getWrappedAdapterAndPosition(position)
        val part = pair.first as? AbstractFormPart<*> ?: return null
        return part[pair.second]
    }

    override fun getItemViewType(position: Int): Int {
        return data.concatAdapter.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return data.concatAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return data.concatAdapter.onBindViewHolder(holder, position)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        return data.concatAdapter.onBindViewHolder(holder, position, payloads)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return data.concatAdapter.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        return data.concatAdapter.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        return data.concatAdapter.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is FormViewHolder) holder.clear()
        return data.concatAdapter.onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        data.concatAdapter.registerAdapterDataObserver(dataObserver)
        data.concatAdapter.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        data.concatAdapter.onDetachedFromRecyclerView(recyclerView)
        data.concatAdapter.unregisterAdapterDataObserver(dataObserver)
    }

    override fun findRelativeAdapterPositionIn(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
        viewHolder: RecyclerView.ViewHolder,
        localPosition: Int
    ) = data.concatAdapter.findRelativeAdapterPositionIn(adapter, viewHolder, localPosition)

    fun visiblePartIndexOf(part: AbstractFormPart<*>): Int {
        var index = 0
        data.concatAdapter.adapters.forEach {
            if (it is AbstractFormPart<*>) {
                if (it == part) return index else if (it.data.isEnabled) index++
            }
        }
        return -1
    }

    fun visiblePartCount(): Int =
        data.concatAdapter.adapters.count { it is AbstractFormPart<*> && it.data.isEnabled }

    fun enabledParts(): List<AbstractFormPart<*>> {
        val parts = ArrayList<AbstractFormPart<*>>()
        data.concatAdapter.adapters.forEach {
            if (it is AbstractFormPart<*> && it.data.isEnabled) {
                parts.add(it)
            }
        }
        return parts
    }

    //<editor-fold desc="类型池 TypePool">

    private val stylePool = ArrayList<AbstractFormStyle>()
    private val typesetPool = ArrayList<AbstractFormTypeset>()
    private val providerPool = ArrayList<AbstractFormItemProvider>()
    private val itemTypePool = ArrayList<Triple<Int, Int, Int>>()

    internal fun getItemViewType4Pool(
        part: AbstractFormPart<*>, style: AbstractFormStyle, item: AbstractFormItem<*>
    ): Int {
        val styleIndex = stylePool.indexOf(style).let {
            if (it < 0) {
                stylePool.add(style)
                stylePool.lastIndex
            } else it
        }
        val typesetClass: KClass<out AbstractFormTypeset> = when {
            item.typeset != null -> item.typeset!!.obtain(item.columnCount, item.columnSize)
            style.typeset != null -> style.typeset!!.obtain(item.columnCount, item.columnSize)
            data.typeset != null -> data.typeset!!.obtain(item.columnCount, item.columnSize)
            else -> FormManager.globalConfig.typeset.obtain(item.columnCount, item.columnSize)
        }
        val typesetIndex = typesetPool.indexOfFirst { it::class == typesetClass }.let {
            if (it < 0) {
                typesetPool.add(typesetClass.java.getDeclaredConstructor().newInstance())
                typesetPool.lastIndex
            } else it
        }
        val providerClass = item.getProvider(part)
        val providerIndex = providerPool.indexOfFirst { it::class == providerClass }.let {
            if (it < 0) {
                providerPool.add(providerClass.java.getDeclaredConstructor().newInstance())
                providerPool.lastIndex
            } else it
        }
        val typeInfo = Triple(styleIndex, typesetIndex, providerIndex)
        return itemTypePool.indexOf(typeInfo).let {
            val index = if (it < 0) {
                itemTypePool.add(typeInfo)
                itemTypePool.lastIndex
            } else it
            -index - 1
        }
    }

    internal fun getStyle(viewType: Int): AbstractFormStyle {
        return stylePool[itemTypePool[abs(viewType) - 1].first]
    }

    internal fun getTypeset(viewType: Int): AbstractFormTypeset {
        return typesetPool[itemTypePool[abs(viewType) - 1].second]
    }

    internal fun getItemProvider(viewType: Int): AbstractFormItemProvider {
        return providerPool[itemTypePool[abs(viewType) - 1].third]
    }

    //</editor-fold>
}