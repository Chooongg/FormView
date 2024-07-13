package com.chooongg.formView.holder

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseFormViewHolder(view: View) : ViewHolder(view) {

    private val views: SparseArray<View> = SparseArray()

    var holderScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        private set

    open fun <T : View> getView(@IdRes viewId: Int): T {
        val view = getViewOrNull<T>(viewId)
        checkNotNull(view) { "No view found with id $viewId" }
        return view
    }

    @Suppress("UNCHECKED_CAST")
    open fun <T : View> getViewOrNull(@IdRes viewId: Int): T? {
        val view = views.get(viewId)
        if (view == null) {
            itemView.findViewById<T>(viewId)?.let {
                views.put(viewId, it)
                return it
            }
        }
        return view as? T
    }

    internal fun clear() {
        onCleared()
    }

    protected open fun onCleared() {
        holderScope.cancel()
        holderScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }
}