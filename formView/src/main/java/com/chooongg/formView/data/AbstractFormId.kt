package com.chooongg.formView.data

import java.util.UUID

abstract class AbstractFormId {
    open val id = UUID.randomUUID().toString()
}