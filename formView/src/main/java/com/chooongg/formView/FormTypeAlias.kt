package com.chooongg.formView

import android.content.Context
import android.content.res.ColorStateList
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IntRange
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.typeset.AbstractFormTypeset
import kotlin.reflect.KClass

/**
 * 列代理Block
 */
typealias FormColumnProviderBlock = (formColumn: Int) -> @receiver:IntRange(from = 1, to = 10) Int

typealias FormTypesetProviderBlock = (formColumn: Int, itemColumn: Int) -> KClass<out AbstractFormTypeset>

/**
 * 颜色Block
 */
typealias FormColorBlock = Context.() -> Int?

/**
 * 颜色状态列表Block
 */
typealias FormColorStateListBlock = Context.() -> ColorStateList?

/**
 * 项目点击时的监听
 */
typealias FormOnItemClickListener = (view: View, item: BaseForm<*>) -> Unit

/**
 * 菜单创建后监听
 */
typealias FormOnMenuCreatedListener = (menu: Menu) -> Unit

/**
 * 菜单点击时的监听
 */
typealias FormOnMenuItemClickListener = (view: View, menuView: View, menuItem: MenuItem) -> Boolean

/**
 * 菜单点击时的监听
 */
typealias FormOnMenuClickListener = (view: View, menuView: View, menuItem: MenuItem, item: BaseForm<*>) -> Unit

/**
 * 联动Block
 */
//typealias FormLinkageBlock = FormLinkage.() -> Unit

/**
 * 内容格式化程序
 */
//typealias FormContentFormatter = Context.(parts: List<AbstractGroupData>?) -> CharSequence?

/**
 * 选项加载 Block
 */
//typealias FormOptionLoader<T> = suspend (BaseOptionForm<T>) -> List<T>?