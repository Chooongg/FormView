package com.chooongg.formView.data

import androidx.recyclerview.widget.ConcatAdapter

class FormData {

    internal val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
    )

    var isEnabled: Boolean = false

}