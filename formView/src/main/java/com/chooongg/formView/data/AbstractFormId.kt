package com.chooongg.formView.data

import java.util.UUID

abstract class AbstractFormId {
    open val id: String? = UUID.randomUUID().toString()
}