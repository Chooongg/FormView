package com.chooongg.formView.item

import com.chooongg.formView.FormOptionLoader
import com.chooongg.formView.enum.FormOptionLoadMode
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.option.FormOptionLoadResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext

abstract class AbstractFormOptionItem<CONTENT, OPTION>(
    name: Any?, field: String?, content: CONTENT?
) : AbstractFormItem<CONTENT>(name, field, content) {

    companion object {
        const val CHANGE_OPTION_PAYLOAD_FLAG = "CHANGE_OPTION"
    }

    abstract fun hasOpenOperation(): Boolean

    open var optionLoadMode = FormOptionLoadMode.EMPTY

    private var optionLoader: FormOptionLoader<CONTENT, OPTION>? = null

    var optionLoadResult: FormOptionLoadResult<OPTION> = FormOptionLoadResult.Wait()
        protected set

    private var localOptions: List<OPTION>? = null

    var options: List<OPTION>?
        get() = localOptions
        set(value) {
            localOptions = value
        }

    fun isNeedToLoadOption(holder: FormViewHolder): Boolean {
        if (optionLoader == null) return false
        if (holder.holderScope.isActive) return false
        return when (optionLoadMode) {
            FormOptionLoadMode.ALWAYS -> true
            FormOptionLoadMode.EMPTY -> options == null
        }
    }

    fun optionLoader(block: FormOptionLoader<CONTENT, OPTION>?) {
        optionLoader = block
    }

    fun clearOptions() {
        localOptions = null
        optionLoadResult = FormOptionLoadResult.Wait()
    }

    open suspend fun loadOption(holder: FormViewHolder, notifyUpdate: () -> Unit) {
        if (optionLoader == null) {
            optionLoadResult = FormOptionLoadResult.Wait()
            notifyUpdate.invoke()
            return
        }
        try {
            optionLoadResult = FormOptionLoadResult.Loading()
            notifyUpdate.invoke()
            val result = withContext(Dispatchers.IO) {
                optionLoader!!.invoke(this@AbstractFormOptionItem)
            }
            optionLoadResult = if (result == null) {
                FormOptionLoadResult.Empty()
            } else FormOptionLoadResult.Success(result)
            notifyUpdate.invoke()
        } catch (e: CancellationException) {
            optionLoadResult = FormOptionLoadResult.Wait()
            notifyUpdate.invoke()
        } catch (e: Exception) {
            optionLoadResult = FormOptionLoadResult.Error(e)
            notifyUpdate.invoke()
        }
    }
}