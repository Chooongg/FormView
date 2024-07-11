package com.chooongg.formView.widget

import android.annotation.SuppressLint
import android.content.Context
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.helper.FormTextAppearanceHelper
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.delegation.IFormMenu
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.ktx.doOnClick
import com.google.android.material.button.MaterialButton

@SuppressLint("RestrictedApi", "ViewConstructor")
class FormMenuView(context: Context, private val style: AbstractFormStyle) : RecyclerView(context) {

    init {
        overScrollMode = View.OVER_SCROLL_NEVER
        isNestedScrollingEnabled = false
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setMenu(
        item: IFormMenu,
        enabled: Boolean,
        onMenuItemClickListener: (view: View, menu: MenuItem) -> Unit
    ) {
        if (item.isMenuVisible(enabled)) {
            val menu = MenuBuilder(context)
            if (item.menu != null) MenuInflater(context).inflate(item.menu!!, menu)
            item.onMenuCreatedListener?.invoke(menu)
            val visibleMenus = menu.visibleItems
            adapter = Adapter(style).apply {
                setData(visibleMenus, item.isMenuEnable(enabled), onMenuItemClickListener)
            }
            visibility = if (visibleMenus.isEmpty()) View.GONE else View.VISIBLE
        } else {
            adapter = null
            visibility = View.GONE
        }
    }

    fun gone() {
        adapter = null
        visibility = View.GONE
    }

    private class Adapter(
        private val style: AbstractFormStyle
    ) : RecyclerView.Adapter<FormViewHolder>(), FormTextAppearanceHelper {

        private var menus: List<MenuItemImpl> = emptyList()

        private var enabled: Boolean = false

        private var onMenuItemClickListener: ((view: View, menu: MenuItem) -> Unit)? = null

        fun setData(
            menus: List<MenuItemImpl>,
            enabled: Boolean,
            onMenuItemClickListener: ((view: View, menu: MenuItem) -> Unit)?
        ) {
            this.menus = menus
            this.enabled = enabled
            this.onMenuItemClickListener = onMenuItemClickListener
        }

        override fun getItemCount(): Int = menus.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FormViewHolder(MaterialButton(
                parent.context, null, com.google.android.material.R.attr.borderlessButtonStyle
            ).apply {
                setTextAppearance(formTextAppearance(R.attr.formTextAppearanceName))
                iconSize = FormUtils.getFontRealHeight(this)
                insetTop = 0
                insetBottom = 0
                minimumWidth =
                    style.paddingInfo.startMedium + style.paddingInfo.endMedium + iconSize
                minimumHeight =
                    style.paddingInfo.topMedium + style.paddingInfo.bottomMedium + iconSize
                minWidth = minimumWidth
                minHeight = minimumHeight
                iconPadding = 0
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
            (holder.itemView as MaterialButton).let {
                it.isEnabled = enabled && menu.isEnabled
                it.icon = icon
                it.text = if (icon == null) menu.titleCondensed else null
                ViewCompat.setTooltipText(it, menu.title ?: menu.titleCondensed)
                it.doOnClick { view ->
                    onMenuItemClickListener?.invoke(view, menu)
                }
            }
        }
    }
}