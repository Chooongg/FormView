package com.chooongg.formView.itemProvider

import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.AbstractFormOptionItem
import kotlinx.coroutines.launch

abstract class AbstractFormOptionItemProvider : AbstractFormItemProvider() {

    protected fun loadOption(holder: FormViewHolder, item: AbstractFormItem<*>) {
        val itemOption = item as? AbstractFormOptionItem<*, *> ?: return
        if (itemOption.isNeedToLoadOption(holder)) {
            holder.holderScope.launch {
                itemOption.loadOption(holder) {
                    holder.itemView.post {

                    }
                }
            }
        }
    }
}