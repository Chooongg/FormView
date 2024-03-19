package com.chooongg.form

import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.data.FormData
import com.chooongg.form.item.BaseForm
import com.chooongg.form.part.AbstractPart
import com.chooongg.form.provider.AbstractFormProvider
import com.chooongg.form.style.AbstractStyle
import com.chooongg.form.typeset.AbstractTypeset
import kotlin.math.abs

class FormAdapter(private val data: FormData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    fun setOnItemClickListener(listener: FormOnItemClickListener?) {
        onItemClickListener = listener
    }

    fun setOnMenuClickListener(listener: FormOnMenuClickListener?) {
        onMenuClickListener = listener
    }

    fun update() {
        partAdapters.forEach { it.update() }
    }

    //<editor-fold desc="覆写 Overwrite">

    val partAdapters get() = data.concatAdapter.adapters.filterIsInstance<AbstractPart<*>>()

    fun getFormItem(position: Int): BaseForm<*>? {
        val pair = data.concatAdapter.getWrappedAdapterAndPosition(position)
        val part = pair.first as? AbstractPart<*> ?: return null
        return part[pair.second]
    }

    override fun getItemCount() = data.concatAdapter.itemCount

    override fun getItemId(position: Int) = data.concatAdapter.getItemId(position)

    override fun getItemViewType(position: Int) = data.concatAdapter.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        data.concatAdapter.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        data.concatAdapter.onBindViewHolder(holder, position)

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>
    ) = data.concatAdapter.onBindViewHolder(holder, position, payloads)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) =
        data.concatAdapter.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) =
        data.concatAdapter.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        data.concatAdapter.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) =
        data.concatAdapter.onViewRecycled(holder)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        data.concatAdapter.registerAdapterDataObserver(dataObserver)
        data.concatAdapter.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        data.concatAdapter.onDetachedFromRecyclerView(recyclerView)
        data.concatAdapter.unregisterAdapterDataObserver(dataObserver)
    }

    fun getWrappedAdapterAndPosition(globalPosition: Int) =
        data.concatAdapter.getWrappedAdapterAndPosition(globalPosition)

    //</editor-fold>

    //<editor-fold desc="类型池 TypePool">

    private val stylePool = ArrayList<AbstractStyle>()
    private val typesetPool = ArrayList<AbstractTypeset>()
    private val providerPool = ArrayList<AbstractFormProvider>()
    private val itemTypePool = ArrayList<Triple<Int, Int, Int>>()

    internal fun getItemViewType4Pool(
        part: AbstractPart<*>, style: AbstractStyle, item: BaseForm<*>
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

    internal fun getStyle4ItemViewType(viewType: Int): AbstractStyle {
        return stylePool[itemTypePool[abs(viewType) - 1].first]
    }

    internal fun getTypeset4ItemViewType(viewType: Int): AbstractTypeset {
        return typesetPool[itemTypePool[abs(viewType) - 1].second]
    }

    internal fun getProvider4ItemViewType(viewType: Int): AbstractFormProvider {
        return providerPool[itemTypePool[abs(viewType) - 1].third]
    }

    override fun findRelativeAdapterPositionIn(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
        viewHolder: RecyclerView.ViewHolder,
        localPosition: Int
    ): Int {
        return data.concatAdapter.findRelativeAdapterPositionIn(adapter, viewHolder, localPosition)
    }

    //</editor-fold>
}