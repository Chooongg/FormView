package com.chooongg.form.item

import android.text.TextUtils
import com.chooongg.form.FormAdapter
import com.chooongg.form.part.AbstractPart
import com.chooongg.form.provider.AbstractFormProvider
import com.chooongg.form.provider.FormTextProvider
import kotlin.reflect.KClass

open class FormText(name: Any?, field: String?) : BaseForm<Any>(name, field) {

    /**
     * 最小行数
     */
    open var minLines: Int = 0

    /**
     * 最大行数
     */
    open var maxLines: Int = Int.MAX_VALUE

    /**
     * 省略号位置
     */
    open var ellipsize: TextUtils.TruncateAt = TextUtils.TruncateAt.END

    override fun getProvider(part: AbstractPart<*>): KClass<out AbstractFormProvider> {
        return FormTextProvider::class
    }
}