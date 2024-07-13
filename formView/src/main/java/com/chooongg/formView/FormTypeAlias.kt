package com.chooongg.formView

import android.content.Context
import android.content.res.ColorStateList
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.GravityInt
import androidx.annotation.IntRange
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.AbstractFormTypeset
import kotlin.reflect.KClass

/**
 * 列代理Block
 */
typealias FormColumnBlock = (columnCount: Int) -> @receiver:IntRange(from = 1, to = 10) Int

typealias FormTypesetBlock = (columnCount: Int, columnSize: Int) -> KClass<out AbstractFormTypeset>

typealias FormEmsSizeBlock = (columnCount: Int, columnSize: Int) -> @receiver:IntRange(from = 1) Int

typealias FormGravityBlock = (columnCount: Int, columnSize: Int) -> @receiver:GravityInt Int

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
typealias FormOnItemClickListener = (view: View, part: AbstractFormPart<*>, item: AbstractFormItem<*>) -> Unit

typealias FormOnItemLongClickListener = (view: View, item: AbstractFormItem<*>) -> Unit

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
typealias FormOnMenuClickListener = (view: View, menuView: View, menuItem: MenuItem, item: AbstractFormItem<*>) -> Unit

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