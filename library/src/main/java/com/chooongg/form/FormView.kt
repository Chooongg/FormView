package com.chooongg.form

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.data.FormData
import com.chooongg.form.layoutManager.FormLayoutManager

class FormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val formAdapter = FormAdapter()

    init {
        super.setLayoutManager(FormLayoutManager(context))
        super.addItemDecoration(FormItemDecoration())
        super.setAdapter(formAdapter)
    }

    fun setData(data: FormData) {
        formAdapter.setData(data)
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        throw IllegalArgumentException("FormView only supports FormLayoutManager")
    }

    override fun getLayoutManager(): FormLayoutManager {
        return super.getLayoutManager() as FormLayoutManager
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        throw IllegalArgumentException("FormView only supports FormAdapter")
    }

    override fun getAdapter(): FormAdapter {
        return formAdapter
    }

    override fun addItemDecoration(decor: ItemDecoration, index: Int) {
        if (decor is FormItemDecoration) throw IllegalArgumentException("FormView only supports single FormItemDecoration")
        super.addItemDecoration(decor, index)
    }

    override fun removeItemDecoration(decor: ItemDecoration) {
        if (decor is FormItemDecoration) throw IllegalArgumentException("FormView must use FormItemDecoration")
        super.removeItemDecoration(decor)
    }
}