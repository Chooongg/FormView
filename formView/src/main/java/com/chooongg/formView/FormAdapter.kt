package com.chooongg.formView

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.data.FormData
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.typeset.AbstractFormTypeset
import kotlin.math.abs

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

    var onItemClickListener: FormOnItemClickListener? = null
        private set

    var onMenuClickListener: FormOnMenuClickListener? = null
        private set

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

    fun getItem(position: Int): BaseForm<*>? {
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
        if (holder is FormItemViewHolder) holder.clear()
        return data.concatAdapter.onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        data.concatAdapter.onAttachedToRecyclerView(recyclerView)
        data.concatAdapter.registerAdapterDataObserver(dataObserver)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        data.concatAdapter.unregisterAdapterDataObserver(dataObserver)
        data.concatAdapter.onDetachedFromRecyclerView(recyclerView)
    }

    //<editor-fold desc="类型池 TypePool">

    private val stylePool = ArrayList<AbstractFormStyle>()
    private val typesetPool = ArrayList<AbstractFormTypeset>()
    private val providerPool = ArrayList<AbstractFormItemProvider>()
    private val itemTypePool = ArrayList<Triple<Int, Int, Int>>()

    internal fun getItemViewType4Pool(
        part: AbstractFormPart<*>, style: AbstractFormStyle, item: BaseForm<*>
    ): Int {
        val styleIndex = stylePool.indexOf(style).let {
            if (it < 0) {
                stylePool.add(style)
                stylePool.lastIndex
            } else it
        }
        val typeset = item.typeset ?: style.config.typeset
        val typesetIndex = typesetPool.indexOf(typeset).let {
            if (it < 0) {
                typesetPool.add(typeset)
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

    override fun findRelativeAdapterPositionIn(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
        viewHolder: RecyclerView.ViewHolder,
        localPosition: Int
    ) = data.concatAdapter.findRelativeAdapterPositionIn(adapter, viewHolder, localPosition)

    //</editor-fold>
}