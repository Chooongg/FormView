package com.chooongg.formView

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.data.FormData
import com.chooongg.formView.holder.FormViewHolder

class FormAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var data: FormData

    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
        }

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

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: FormData) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        if (this::data.isInitialized) data.concatAdapter.itemCount else 0

    override fun getItemId(position: Int): Long {
        return data.concatAdapter.getItemId(position)
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
        if (holder is FormViewHolder) {
            holder.clear()
        }
        return data.concatAdapter.onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        registerAdapterDataObserver(dataObserver)
        data.concatAdapter.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        data.concatAdapter.onDetachedFromRecyclerView(recyclerView)
        unregisterAdapterDataObserver(dataObserver)
    }
}