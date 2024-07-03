package com.chooongg.formView.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.RippleDrawable
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.ktx.attrColorStateList

class FormMenuView(context: Context) : RecyclerView(context) {

    private val menuAdapter = Adapter()

    init {
        overScrollMode = View.OVER_SCROLL_NEVER
        isNestedScrollingEnabled = false
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = menuAdapter
    }

    private class Adapter(

    ) : RecyclerView.Adapter<FormViewHolder>() {

        private var menus = ArrayList<MenuItemImpl>()

        override fun getItemCount(): Int = menus.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FormViewHolder(AppCompatImageButton(parent.context).apply {
                background = RippleDrawable(
                    attrColorStateList(android.R.attr.colorControlHighlight)
                        ?: ColorStateList.valueOf(Color.GRAY), null, null
                ).apply {
                    radius = 20
                }

            })

        override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        }
    }
}