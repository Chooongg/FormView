package com.chooongg.formView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntRange
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.data.FormData
import com.chooongg.formView.layoutManager.FormItemDecoration
import com.chooongg.formView.layoutManager.FormLayoutManager

class FormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val layoutManager: FormLayoutManager =
        context.obtainStyledAttributes(attrs, R.styleable.FormView, 0, 0).use {
            val column = it.getInt(R.styleable.FormView_fixedColumn, -1)
            val columnWidth = it.getDimensionPixelSize(
                R.styleable.FormView_columnWidth,
                context.resources.getDimensionPixelSize(R.dimen.formColumnWidth)
            )
            FormLayoutManager(
                context, if (column == -1 && columnWidth == -1) 1 else column, columnWidth
            )
        }


    val formColumn get() = layoutManager.columnCount

    var onItemClickListener: FormOnItemClickListener? = null
        private set

    var onMenuClickListener: FormOnMenuClickListener? = null
        private set

    init {
        layoutManager.setPadding(paddingLeft, paddingTop, paddingEnd, paddingBottom)
        super.setLayoutManager(layoutManager)
        super.setPadding(0, 0, 0, 0)
        super.addItemDecoration(FormItemDecoration(context))
    }

    fun setFixedColumn(
        @IntRange(1, FormManager.FORM_MAX_COLUMN_COUNT.toLong()) column: Int
    ) {
        layoutManager.fixedColumn = column
    }

    fun setColumnWidth(@Px width: Int) {
        layoutManager.columnWidth = width
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        layoutManager.setPadding(left, top, right, bottom)
        requestLayout()
    }

    override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
        if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            layoutManager.setPadding(end, top, start, bottom)
        } else {
            layoutManager.setPadding(start, top, end, bottom)
        }
        requestLayout()
    }

    fun setData(data: FormData) {
        super.setAdapter(FormAdapter(data).apply {
            layoutManager.adapter = this
            columnCount = layoutManager.columnCount
        })
        isEnabled = data.isEnabled
    }

    fun setData(block: FormData.() -> Unit) {
        setData(FormData(block))
    }

    override fun setEnabled(enabled: Boolean) {
        adapter?.data?.isEnabled = enabled
    }

    override fun isEnabled(): Boolean {
        return adapter?.data?.isEnabled ?: false
    }

    @Deprecated("this method external calls not supported")
    override fun setAdapter(adapter: Adapter<*>?) {
        throw IllegalArgumentException("this method external calls not supported")
    }

    override fun getAdapter(): FormAdapter? {
        return super.getAdapter() as? FormAdapter
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