package com.chooongg.formView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.data.FormData
import com.chooongg.formView.decoration.FormItemDecoration
import com.chooongg.formView.layoutManager.FormLayoutManager

class FormView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RecyclerView(context, attrs, defStyleAttr) {

    init {
        super.setLayoutManager(FormLayoutManager(context))
        super.addItemDecoration(FormItemDecoration())
        super.setAdapter(FormAdapter())
    }

    fun setData(data: FormData) {
        adapter.setData(data)
    }

    fun setData(block: FormData.() -> Unit) {

    }

    @Deprecated("this method external calls not supported")
    override fun setAdapter(adapter: Adapter<*>?) {
        throw IllegalArgumentException("this method external calls not supported")
    }

    override fun getAdapter(): FormAdapter {
        return super.getAdapter() as FormAdapter
    }

    @Deprecated("this method external calls not supported")
    override fun setLayoutManager(layout: LayoutManager?) {
        throw IllegalArgumentException("this method external calls not supported")
    }

    override fun getLayoutManager(): FormLayoutManager {
        return super.getLayoutManager() as FormLayoutManager
    }

    override fun addItemDecoration(decor: ItemDecoration, index: Int) {
        super.addItemDecoration(decor, index + 1)
    }

    override fun removeItemDecorationAt(index: Int) {
        super.removeItemDecorationAt(index + 1)
    }

    override fun getItemDecorationCount(): Int {
        return super.getItemDecorationCount() + 1
    }

    override fun getItemDecorationAt(index: Int): ItemDecoration {
        return super.getItemDecorationAt(index + 1)
    }
}