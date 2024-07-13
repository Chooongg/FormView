package com.chooongg.formView.data

import android.view.ViewGroup
import android.widget.Space
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormView
import com.chooongg.formView.config.FormConfigImpl
import com.chooongg.formView.config.IFormConfig
import com.chooongg.formView.holder.BaseFormViewHolder
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.part.FormPart
import com.chooongg.formView.style.AbstractFormStyle

class FormData(block: (FormData.() -> Unit)? = null) : IFormConfig by FormConfigImpl() {

    internal val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
    ).apply { addAdapter(TopPlaceHolder()) }

    val parts get() = concatAdapter.adapters.filterIsInstance<AbstractFormPart<*>>()

    var isEnabled: Boolean = true
        set(value) {
            if (field != value) {
                field = value
                parts.forEach { it.isEnabled = value }
            }
        }

    var style: AbstractFormStyle? = null

    init {
        block?.invoke(this)
    }

    fun getWrappedAdapterAndPosition(globalPosition: Int) =
        concatAdapter.getWrappedAdapterAndPosition(globalPosition)

    fun part(part: AbstractFormPart<*>) {
        part.isEnabled = isEnabled
        concatAdapter.addAdapter(part)
    }

    fun part(
        style: AbstractFormStyle = this.style ?: FormManager.defaultStyle,
        block: FormPartData.() -> Unit
    ) = part(FormPart(style, FormPartData().apply(block), isEnabled))

    fun clearTopPlaceHolder() {
        if (concatAdapter.adapters.isNotEmpty()) {
            val childAdapter = concatAdapter.adapters[0]
            if (childAdapter is TopPlaceHolder) {
                concatAdapter.removeAdapter(childAdapter)
            }
        }
    }

    internal fun hasTopPlaceHolder(): Boolean {
        return concatAdapter.adapters.isNotEmpty() && concatAdapter.adapters[0] is TopPlaceHolder
    }

    internal class TopPlaceHolder : RecyclerView.Adapter<BaseFormViewHolder>() {

        private var _formView: FormView? = null

        override fun getItemCount(): Int = 1
        override fun onBindViewHolder(holder: BaseFormViewHolder, position: Int) = Unit
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseFormViewHolder {
            return BaseFormViewHolder(Space(parent.context).apply {
                layoutParams = GridLayoutManager.LayoutParams(-1, 1)
            })
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            _formView = recyclerView as? FormView
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            _formView = null
        }
    }
}