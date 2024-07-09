package com.chooongg.formView.data

import androidx.recyclerview.widget.ConcatAdapter
import com.chooongg.formView.FormManager
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.part.FormPart
import com.chooongg.formView.style.AbstractFormStyle

class FormData {

    internal val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
    )/*.apply { addAdapter(FormTopBottomDecoration()) }*/

    val parts get() = concatAdapter.adapters.filterIsInstance<AbstractFormPart<*>>()

    var isEnabled: Boolean = true
        set(value) {
            if (field != value) {
                field = value
                parts.forEach { it.isEnabled = value }
            }
        }

    fun addPart(part: AbstractFormPart<*>) {
        concatAdapter.addAdapter(part)
    }

    fun addPart(
        style: AbstractFormStyle = FormManager.defaultStyle, block: FormPartData.() -> Unit
    ) = addPart(FormPart(style, FormPartData().apply(block), isEnabled))


    fun getWrappedAdapterAndPosition(globalPosition: Int) =
        concatAdapter.getWrappedAdapterAndPosition(globalPosition)
}