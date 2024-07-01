package com.chooongg.formView.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class FormViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    var holderScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        private set

    protected open fun onCleared() {
        holderScope.cancel()
        holderScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }

    internal fun clear() {
        onCleared()
    }

}