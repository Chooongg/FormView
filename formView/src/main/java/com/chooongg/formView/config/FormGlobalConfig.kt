package com.chooongg.formView.config

import android.view.Gravity
import com.chooongg.formView.enum.FormEmsSize
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.formatter.name.FormNameFormatter
import com.chooongg.formView.formatter.name.NormalFormNameFormatter
import com.chooongg.formView.provider.groupTitle.AbstractGroupTitleProvider
import com.chooongg.formView.provider.groupTitle.FormNormalChildTitleProvider
import com.chooongg.formView.provider.groupTitle.FormNormalGroupTitleProvider
import com.chooongg.formView.provider.nestedTitle.AbstractNestedTitleProvider
import com.chooongg.formView.provider.nestedTitle.FormNormalNestedTitleProvider
import com.chooongg.formView.typeset.FormHorizontalTypeset
import com.chooongg.formView.typeset.FormVerticalTypeset
import com.google.android.material.button.MaterialButton

class FormGlobalConfig {

    /**
     * 名称格式化程序
     */
    var nameFormatter: FormNameFormatter = NormalFormNameFormatter()

    /**
     * 组标题视图提供器
     */
    var groupTitleProvider: AbstractGroupTitleProvider = FormNormalGroupTitleProvider()

    /**
     * 嵌套视图中组标题视图提供器
     */
    var childTitleProvider: AbstractGroupTitleProvider = FormNormalChildTitleProvider()

    /**
     * 嵌套视图提供器
     */
    var nestedTitleProvider: AbstractNestedTitleProvider = FormNormalNestedTitleProvider()

    /**
     * EMS 值
     */
    var emsSize: FormEmsSize? = null

    /**
     * 名称图标重力
     */
    @MaterialButton.IconGravity
    var nameIconGravity: Int = MaterialButton.ICON_GRAVITY_TEXT_START

    /**
     * 名称重力
     */
    var nameGravity: FormGravity = FormGravity(Gravity.NO_GRAVITY)

    /**
     * 内容重力
     */
    var contentGravity: FormGravity = FormGravity { columnCount, _ ->
        if (columnCount > 1) Gravity.NO_GRAVITY else Gravity.END
    }

    /**
     * 排版
     */
    var typeset: FormTypeset = FormTypeset { columnCount, _ ->
        if (columnCount > 1) FormVerticalTypeset::class else FormHorizontalTypeset::class
    }
}