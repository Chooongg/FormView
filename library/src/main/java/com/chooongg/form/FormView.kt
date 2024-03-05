package com.chooongg.form

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.layoutManager.FormLayoutManager

class FormView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {



    init {
        layoutManager = FormLayoutManager(context)

    }

}