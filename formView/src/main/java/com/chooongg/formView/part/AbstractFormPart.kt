package com.chooongg.formView.part

import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.holder.FormViewHolder

abstract class AbstractFormPart<DATA>() : RecyclerView.Adapter<FormViewHolder>() {

    var isEnabled = false
        internal set(value) {
            if (field != value) {
                field = value
                update()
            }
        }

    fun update() {
        executeUpdate {

        }
    }

    protected abstract fun executeUpdate(commitCallback: Runnable)
}