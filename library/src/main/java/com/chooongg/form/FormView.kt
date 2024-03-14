package com.chooongg.form

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.form.data.FormData
import com.chooongg.form.layoutManager.FormLayoutManager

class FormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        super.setLayoutManager(FormLayoutManager(context))
        super.addItemDecoration(FormItemDecoration())
    }

    fun setData(data: FormData) {
        super.setAdapter(FormAdapter(data))
    }

    fun setData(block:FormData.() -> Unit){
        setData(FormData().apply(block))
    }

    override fun swapAdapter(adapter: Adapter<*>?, removeAndRecycleExistingViews: Boolean) {
        throw IllegalArgumentException("this method external calls not supported")
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        throw IllegalArgumentException("this method external calls not supported")
    }

    override fun getLayoutManager(): FormLayoutManager {
        return super.getLayoutManager() as FormLayoutManager
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        throw IllegalArgumentException("this method external calls not supported")
    }

    override fun getAdapter(): FormAdapter? {
        return super.getAdapter() as? FormAdapter
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