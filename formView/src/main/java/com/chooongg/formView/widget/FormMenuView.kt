package com.chooongg.formView.widget

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.button.MaterialButton

@SuppressLint("ViewConstructor")
class FormMenuView(context: Context, style: AbstractFormStyle) : RecyclerView(context) {

    private val menuAdapter = Adapter(style)

    init {
        overScrollMode = View.OVER_SCROLL_NEVER
        isNestedScrollingEnabled = false
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = menuAdapter
    }

    fun setMenu(item: BaseForm<*>) {

    }

    private class Adapter(
        private val style: AbstractFormStyle
    ) : RecyclerView.Adapter<FormViewHolder>(), FormTextAppearanceHelper {

        private var menus = ArrayList<MenuItemImpl>()

        override fun getItemCount(): Int = menus.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FormViewHolder(MaterialButton(
                parent.context, null, com.google.android.material.R.attr.borderlessButtonStyle
            ).apply {
                insetTop = 0
                insetBottom = 0
                iconPadding = 0
                setTextAppearance(formTextAppearance(R.attr.formTextAppearanceContent))
                val textHeight = FormUtils.getFontRealHeight(this)
                minWidth = 0
                minHeight = 0
                minimumWidth = 0
                minimumHeight = 0
                iconSize = textHeight
                setPaddingRelative(
                    style.paddingInfo.startMedium,
                    style.paddingInfo.topMedium,
                    style.paddingInfo.endMedium,
                    style.paddingInfo.bottomMedium
                )
            })

        @SuppressLint("RestrictedApi")
        override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
            val menu = menus[position]
            val icon = menu.getIcon()
            if (icon == null) {

            } else {

            }
        }
    }
}