package com.chooongg.formView.option

sealed class FormOptionLoadResult<T> {

    /**
     * 等待状态
     */
    class Wait<T> : FormOptionLoadResult<T>()

    /**
     * 加载中
     */
    class Loading<T> : FormOptionLoadResult<T>()

    /**
     * 空结果
     */
    class Empty<T> : FormOptionLoadResult<T>()

    /**
     * 成功结果
     */
    class Success<T>(val options: List<T>) : FormOptionLoadResult<T>()

    /**
     * 错误结果
     */
    class Error<T>(val e: Exception) : FormOptionLoadResult<T>()
}

