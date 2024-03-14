package com.chooongg.form.data

import java.util.UUID

abstract class AbstractFormId {
    /**
     * 唯一标识
     */
    open val id = UUID.randomUUID().toString()
}