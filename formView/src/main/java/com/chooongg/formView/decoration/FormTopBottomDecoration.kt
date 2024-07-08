package com.chooongg.formView.decoration

import android.view.ViewGroup
import android.widget.Space
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.holder.FormViewHolder
import com.google.android.flexbox.FlexboxLayoutManager

class FormTopBottomDecoration : RecyclerView.Adapter<FormViewHolder>() {

    override fun getItemViewType(position: Int) = Int.MIN_VALUE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FormViewHolder(
        Space(parent.context)
    ).apply {
        itemView.layoutParams = FlexboxLayoutManager.LayoutParams(-1, -2)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) = Unit
}