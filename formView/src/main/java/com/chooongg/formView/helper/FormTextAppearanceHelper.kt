package com.chooongg.formView.helper

import android.content.Context
import android.view.View
import androidx.annotation.AttrRes
import androidx.core.content.res.use
import com.chooongg.formView.R

interface FormTextAppearanceHelper {

    fun View.formTextAppearance(@AttrRes resId: Int): Int = context.formTextAppearance(resId)

    fun Context.formTextAppearance(@AttrRes resId: Int): Int =
        obtainStyledAttributes(intArrayOf(resId)).use {
            it.getResourceId(
                0, when (resId) {
                    R.attr.formTextAppearanceGroupTitle -> R.style.Form_TextAppearance_GroupTitle
                    R.attr.formTextAppearanceChildTitle -> R.style.Form_TextAppearance_ChildTitle
                    R.attr.formTextAppearanceName -> R.style.Form_TextAppearance_Name
                    R.attr.formTextAppearanceContent -> R.style.Form_TextAppearance_Content
                    R.attr.formTextAppearanceHint -> R.style.Form_TextAppearance_Hint
                    R.attr.formTextAppearanceLabel -> R.style.Form_TextAppearance_Label
                    R.attr.formTextAppearanceTip -> R.style.Form_TextAppearance_Tip
                    R.attr.formTextAppearanceBadge -> R.style.Form_TextAppearance_Badge
                    R.attr.formTextAppearanceSearch -> R.style.Form_TextAppearance_Search
                    R.attr.formTextAppearancePrefix -> R.style.Form_TextAppearance_Prefix
                    R.attr.formTextAppearanceSuffix -> R.style.Form_TextAppearance_Suffix
                    R.attr.formTextAppearancePlaceholder -> R.style.Form_TextAppearance_Placeholder
                    R.attr.formTextAppearanceCounter -> R.style.Form_TextAppearance_Counter
                    else -> com.google.android.material.R.style.TextAppearance_Material3_BodyMedium
                }
            )
        }
}