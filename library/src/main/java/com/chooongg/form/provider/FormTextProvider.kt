package com.chooongg.form.provider

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.chooongg.form.FormManager
import com.chooongg.form.R
import com.chooongg.form.holder.FormViewHolder
import com.chooongg.form.item.BaseForm
import com.chooongg.form.item.FormText
import com.chooongg.form.style.AbstractStyle
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.CoroutineScope

class FormTextProvider : AbstractFormProvider() {

    override fun onCreateViewHolder(style: AbstractStyle, parent: ViewGroup): View {
        return MaterialTextView(parent.context).apply {
            id = R.id.formInternalContentView
            setTextAppearance(formTextAppearance(R.attr.formTextAppearanceContent))
            setPaddingRelative(
                style.padding.startMedium,
                style.padding.topMedium,
                style.padding.endMedium,
                style.padding.bottomMedium
            )
        }
    }

    override fun onBindViewHolder(
        scope: CoroutineScope, holder: FormViewHolder, item: BaseForm<*>, adapterEnabled: Boolean
    ) {
        val itemText = item as? FormText
        with(holder.findViewById<MaterialTextView>(R.id.formInternalContentView)) {
            minLines = itemText?.minLines ?: 0
            maxLines = itemText?.maxLines ?: Int.MAX_VALUE
            ellipsize = itemText?.ellipsize ?: TextUtils.TruncateAt.END
            text = FormManager.extractText(context, item.content)
            hint = FormManager.extractText(context, item.hint)
        }
    }

    override fun onBindContentViewGravity(
        holder: FormViewHolder, item: BaseForm<*>, isMultiColumn: Boolean
    ) {
        with(holder.findViewById<MaterialTextView>(R.id.formInternalContentView)) {
            gravity = item.getContentGravity(holder, isMultiColumn)
        }
    }
}