package com.chooongg.formView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntRange
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.data.FormData
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.layoutManager.FormItemAnimator
import com.chooongg.formView.layoutManager.FormItemDecoration
import com.chooongg.formView.layoutManager.FormLayoutManager
import com.chooongg.formView.listener.FormOnItemClickListener
import com.chooongg.formView.listener.FormOnMenuClickListener
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.nestedTitle.AbstractNestedTitleProvider
import com.chooongg.formView.style.AbstractFormStyle

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


    val columnCount get() = layoutManager.columnCount

    var onItemClickListener: FormOnItemClickListener? = null
        private set

    var onMenuClickListener: FormOnMenuClickListener? = null
        private set

    init {
        layoutManager.setPadding(paddingLeft, paddingTop, paddingEnd, paddingBottom)
        super.setLayoutManager(layoutManager)
        super.setPadding(0, 0, 0, 0)
        super.addItemDecoration(FormItemDecoration(context))
        itemAnimator = FormItemAnimator()
    }

    fun setFixedColumn(
        @IntRange(1, FormManager.FORM_MAX_COLUMN_COUNT.toLong()) column: Int
    ) {
        layoutManager.fixedColumn = column
    }

    fun setColumnWidth(@Px width: Int) {
        layoutManager.columnWidth = width
    }

    fun setOnItemClickListener(listener: FormOnItemClickListener?) {
        onItemClickListener = listener
    }

    fun setOnMenuClickListener(listener: FormOnMenuClickListener?) {
        onMenuClickListener = listener
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
        })
    }

    fun setData(
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
        setData(
            FormData(
                style,
                nameFormatter,
                groupTitleProvider,
                childTitleProvider,
                nestedTitleProvider,
                emsSize,
                nameIconGravity,
                nameGravity,
                contentGravity,
                typeset,
                showProgress,
                block
            )
        )
    }

    override fun setEnabled(enabled: Boolean) {
        adapter?.data?.isEnabled = enabled
    }

    override fun isEnabled(): Boolean {
        return adapter?.data?.isEnabled ?: false
    }

    fun update() {
        adapter?.update()
    }

    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("this method external calls not supported")
    override fun setAdapter(adapter: Adapter<*>?) {
        throw IllegalArgumentException("this method external calls not supported")
    }

    override fun getAdapter(): FormAdapter? {
        return super.getAdapter() as? FormAdapter
    }

    @Suppress("DeprecatedCallableAddReplaceWith")
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