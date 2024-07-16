package com.chooongg.formView.item

import com.chooongg.formView.FormColorStateListBlock
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormActionProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.FormNoneTypeset
import com.google.android.material.badge.BadgeDrawable
import kotlin.reflect.KClass

class FormAction(
    name: Any?, field: String?, content: Any?
) : AbstractFormItem<Any>(name, field, content) {

    /**
     * -1: 无文本
     * null: 隐藏
     */
    var badge: Any? = null

    var badgeEnabled: Boolean = true

    var badgeColor: FormColorStateListBlock? = null

    var badgeTextColor: FormColorStateListBlock? = null

    var badgeMaxNumber: Int = BadgeDrawable.BADGE_CONTENT_NOT_TRUNCATED

    var badgeMaxCharacterCount: Int = BadgeDrawable.BADGE_CONTENT_NOT_TRUNCATED

    /**
     * 显示下一步图标
     */
    var showNextLevelIcon: Boolean = true

    /**
     * 下一级图标
     */
    var nextLevelIcon: Any? = null

    override var isEnabledItemClick: Boolean = true

    override val disableTypesetConfigMenu: Boolean = true

    override var fillEdgesPadding: Boolean = false

    override var typeset: FormTypeset? = FormTypeset(FormNoneTypeset::class)

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormActionProvider::class
    }
}