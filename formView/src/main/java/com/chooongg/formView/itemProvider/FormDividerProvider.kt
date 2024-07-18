package com.chooongg.formView.itemProvider

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.use
import com.chooongg.formView.R
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.FormDivider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.ktx.attrResourcesId
import com.chooongg.ktx.dp2px
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.theme.overlay.MaterialThemeOverlay

class FormDividerProvider : AbstractFormItemProvider() {

    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View {
        return MaterialDivider(parent.context).apply {
            id = R.id.formContentView
        }
    }

    override fun onBindViewHolder(
        holder: FormViewHolder, item: AbstractFormItem<*>
    ) {
        with(holder.getView<MaterialDivider>(R.id.formContentView)) {
            configDividerStyle(this, item)
            if (item is FormDivider) {
                if (item.insetStartResId != null) {
                    setDividerInsetStartResource(item.insetStartResId!!)
                } else if (item.insetStartFromPadding) {
                    dividerInsetStart = holder.style.padding.start
                } else dividerInsetStart = 0
                if (item.insetEndResId != null) {
                    setDividerInsetEndResource(item.insetEndResId!!)
                } else if (item.insetEndFromPadding) {
                    dividerInsetEnd = holder.style.padding.end
                } else dividerInsetEnd = 0
            } else {
                dividerInsetStart = 0
                dividerInsetEnd = 0
            }
        }
    }

    private fun configDividerStyle(view: MaterialDivider, item: AbstractFormItem<*>) {
        val style = (item as? FormDivider)?.dividerStyle ?: view.attrResourcesId(
            com.google.android.material.R.attr.materialDividerStyle,
            com.google.android.material.R.style.Widget_Material3_MaterialDivider
        )
        val wrap = MaterialThemeOverlay.wrap(view.context, null, 0, style)
        view.dividerColor = wrap.obtainStyledAttributes(
            style, intArrayOf(com.google.android.material.R.attr.dividerColor)
        ).use { it.getColor(0, Color.GRAY) }
        view.dividerThickness = wrap.obtainStyledAttributes(
            style, intArrayOf(com.google.android.material.R.attr.dividerThickness)
        ).use { it.getDimensionPixelSize(0, dp2px(1f)) }
    }
}