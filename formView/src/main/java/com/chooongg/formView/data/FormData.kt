package com.chooongg.formView.data

import android.view.ViewGroup
import android.widget.Space
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormView
import com.chooongg.formView.config.FormDataConfig
import com.chooongg.formView.config.IFormDataConfig
import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.holder.BaseFormViewHolder
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.part.FormPart
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nestedTitle.AbstractNestedTitleProvider
import com.chooongg.formView.style.AbstractFormStyle

class FormData(
    style: AbstractFormStyle? = null,
    nameFormatter: FormNameFormatter? = null,
    groupTitleProvider: AbstractGroupTitleProvider? = null,
    childTitleProvider: AbstractGroupTitleProvider? = null,
    nestedTitleProvider: AbstractNestedTitleProvider? = null,
    emsSize: FormEmsSize? = null,
    nameIconGravity: Int? = null,
    nameGravity: FormGravity? = null,
    contentGravity: FormGravity? = null,
    typeset: FormTypeset? = null,
    showProgress: Boolean = true,
    block: FormData.() -> Unit
) {

    internal val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
    ).apply {
        addAdapter(TopBottomPlaceHolder())
        addAdapter(TopBottomPlaceHolder())
    }

    val parts get() = concatAdapter.adapters.filterIsInstance<AbstractFormPart<*>>()

    var isEnabled: Boolean = true
        set(value) {
            if (field != value) {
                field = value
                parts.forEach { it.isEnabled = value }
            }
        }

    val dataConfig: IFormDataConfig = FormDataConfig(
        style,
        nameFormatter,
        groupTitleProvider,
        childTitleProvider,
        nestedTitleProvider,
        emsSize,
        nameIconGravity,
        nameGravity,
        contentGravity,
        typeset
    )

    private val headerAdapters = ArrayList<RecyclerView.Adapter<*>>()

    private var footerAdapters = ArrayList<RecyclerView.Adapter<*>>()

    init {
        block.invoke(this)
    }

    fun getWrappedAdapterAndPosition(globalPosition: Int) =
        concatAdapter.getWrappedAdapterAndPosition(globalPosition)

    fun part(part: AbstractFormPart<*>) {
        part.isEnabled = isEnabled
        concatAdapter.addAdapter(concatAdapter.adapters.size - footerAdapters.size - 1, part)
    }

    fun part(
        style: AbstractFormStyle = this.dataConfig.style ?: FormManager.defaultStyle,
        nameFormatter: FormNameFormatter? = null,
        groupTitleProvider: AbstractGroupTitleProvider? = null,
        childTitleProvider: AbstractGroupTitleProvider? = null,
        nestedTitleProvider: AbstractNestedTitleProvider? = null,
        emsSize: FormEmsSize? = null,
        nameIconGravity: Int? = null,
        nameGravity: FormGravity? = null,
        contentGravity: FormGravity? = null,
        typeset: FormTypeset? = null,
        column: FormColumn? = null,
        block: FormPartData.() -> Unit
    ) = part(
        FormPart(
            style, FormPartData(
                nameFormatter,
                groupTitleProvider,
                childTitleProvider,
                nestedTitleProvider,
                emsSize,
                nameIconGravity,
                nameGravity,
                contentGravity,
                typeset,
                column,
                block
            ), isEnabled
        )
    )

    fun removeChildAdapter(adapter: RecyclerView.Adapter<*>) {
        concatAdapter.removeAdapter(adapter)
        if (headerAdapters.contains(adapter)) {
            headerAdapters.remove(adapter)
        }
        if (footerAdapters.contains(adapter)) {
            footerAdapters.remove(adapter)
        }
    }

    fun isHasHeaderChildAdapter() = headerAdapters.isNotEmpty()

    fun addHeaderChildAdapter(adapter: RecyclerView.Adapter<*>) {
        concatAdapter.addAdapter(headerAdapters.size, adapter)
        headerAdapters.add(adapter)
    }

    fun clearHeaderChildAdapter() {
        if (concatAdapter.adapters.isEmpty()) return
        footerAdapters.forEach { concatAdapter.removeAdapter(it) }
        footerAdapters.clear()
    }

    fun isHasFooterChildAdapter() = footerAdapters.isNotEmpty()

    fun addFooterChildAdapter(adapter: RecyclerView.Adapter<*>) {
        concatAdapter.addAdapter(footerAdapters.size, adapter)
        footerAdapters.add(adapter)
    }

    fun clearFooterChildAdapter() {
        if (concatAdapter.adapters.isEmpty()) return
        footerAdapters.forEach { concatAdapter.removeAdapter(it) }
        footerAdapters.clear()
    }

    internal fun hasTopPlaceHolder(): Boolean {
        return concatAdapter.adapters.isNotEmpty() && concatAdapter.adapters[0] is TopBottomPlaceHolder
    }

    internal class TopBottomPlaceHolder : RecyclerView.Adapter<BaseFormViewHolder>() {

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