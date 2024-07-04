package com.chooongg.formView.part

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormAdapter
import com.chooongg.formView.data.AbstractFormId
import com.chooongg.formView.data.IFormPart
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class AbstractFormPart<DATA>(
    val adapter: FormAdapter, val style: AbstractFormStyle, var data: DATA
) : RecyclerView.Adapter<FormItemViewHolder>() where DATA : IFormPart, DATA : AbstractFormId {

    var isEnabled = false
        internal set(value) {
            if (field != value) {
                field = value
                update()
            }
        }

    var partScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        internal set

    protected val asyncDiffer = AsyncListDiffer(object : ListUpdateCallback {
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

    fun update() {
        executeUpdate {

        }
    }

    protected abstract fun executeUpdate(commitCallback: Runnable)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {

    }

    override fun onViewRecycled(holder: FormItemViewHolder) {
        holder.clear()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        partScope.cancel()
        partScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }
}