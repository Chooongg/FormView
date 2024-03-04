package com.chooongg.form.part

import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.data.IFormPart
import com.chooongg.form.holder.FormViewHolder

abstract class AbstractPart<DATA : IFormPart> : RecyclerView.Adapter<FormViewHolder>() {
}