package com.chooongg.form.data

import androidx.recyclerview.widget.ConcatAdapter
import com.chooongg.form.FormManager
import com.chooongg.form.item.BaseForm
import com.chooongg.form.part.AbstractPart
import com.chooongg.form.part.FormPart
import com.chooongg.form.style.AbstractStyle

/**
 * 表单数据
 */
class FormData {

    internal val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
    )

    val partAdapters get() = concatAdapter.adapters.filterIsInstance<AbstractPart<*>>()

    /**
     * 是否启用
     */
    var isEnabled: Boolean = false

    fun addPart(part: AbstractPart<*>) {
        concatAdapter.addAdapter(part)
        part.update()
    }

    fun addPart(style: AbstractStyle = FormManager.defaultStyle, block: FormPartData.() -> Unit) {
        addPart(FormPart(style, FormPartData().apply(block)).apply { update() })
    }

    fun getWrappedAdapterAndPosition(globalPosition: Int) =
        concatAdapter.getWrappedAdapterAndPosition(globalPosition)

    operator fun get(field: String): Pair<AbstractPart<*>, BaseForm<*>>? {
        partAdapters.forEach {
            val item = it[field]
            if (item != null) return Pair(it, item)
        }
        return null
    }

    operator fun contains(field: String): Boolean {
        partAdapters.forEach {
            if (field in it) return true
        }
        return false
    }

    operator fun contains(item: BaseForm<*>): Boolean {
        partAdapters.forEach {
            if (item in it) return true
        }
        return false
    }

    fun findPart(field: String): AbstractPart<*>? {
        partAdapters.forEach {
            if (field in it) return it
        }
        return null
    }

    fun findPart(item: BaseForm<*>): AbstractPart<*>? {
        partAdapters.forEach {
            if (item in it) return it
        }
        return null
    }
}