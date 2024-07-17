package com.chooongg.formView.item

import androidx.annotation.CallSuper
import com.chooongg.formView.FormColumnBlock
import com.chooongg.formView.data.AbstractFormId
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.data.FormExtensionMap
import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.itemDelegation.FormContentGravityImpl
import com.chooongg.formView.itemDelegation.FormEmsImpl
import com.chooongg.formView.itemDelegation.FormIconImpl
import com.chooongg.formView.itemDelegation.FormMenuImpl
import com.chooongg.formView.itemDelegation.FormNameImpl
import com.chooongg.formView.itemDelegation.IFormContentGravity
import com.chooongg.formView.itemDelegation.IFormEms
import com.chooongg.formView.itemDelegation.IFormField
import com.chooongg.formView.itemDelegation.IFormIcon
import com.chooongg.formView.itemDelegation.IFormMenu
import com.chooongg.formView.itemDelegation.IFormName
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.listener.FormOnItemClickListener
import com.chooongg.formView.part.AbstractFormPart
import kotlin.reflect.KClass

abstract class AbstractFormItem<CONTENT>(
    /**
     * 名称
     */
    override var name: Any?,
    /**
     * 字段
     */
    override var field: String?,
    /**
     * 内容
     */
    var content: CONTENT?
) : AbstractFormId(), IFormName by FormNameImpl(), IFormField, IFormIcon by FormIconImpl(),
    IFormEms by FormEmsImpl(), IFormMenu by FormMenuImpl(),
    IFormContentGravity by FormContentGravityImpl() {

    //<editor-fold desc="基础 Basic">

    /**
     * 获取提供程序
     */
    abstract fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider>

    /**
     * 扩展内容
     */
    val extensionContent = FormExtensionMap()

    /**
     * 可见模式
     */
    open var visibilityMode: FormVisibilityMode = FormVisibilityMode.ALWAYS

    /**
     * 启用模式
     */
    open var enableMode: FormEnableMode = FormEnableMode.ENABLED

    /**
     * 是否为必填项
     */
    open var required: Boolean = false

    /**
     * 是否禁用排版配置菜单
     */
    open val disableTypesetConfigMenu: Boolean = false

    internal fun isVisible(adapterEnabled: Boolean): Boolean {
        return when (visibilityMode) {
            FormVisibilityMode.ALWAYS -> true
            FormVisibilityMode.ENABLED -> adapterEnabled
            FormVisibilityMode.DISABLED -> !adapterEnabled
            FormVisibilityMode.NEVER -> false
        }
    }

    internal fun isEnable(adapterEnabled: Boolean): Boolean {
        return when (enableMode) {
            FormEnableMode.ALWAYS -> true
            FormEnableMode.ENABLED -> adapterEnabled
            FormEnableMode.DISABLED -> !adapterEnabled
            FormEnableMode.NEVER -> false
        }
    }

    /**
     * 获取内容文本
     */
    open fun getContentText(): Any? {
        return content
    }

    //</editor-fold>

    //<editor-fold desc="排版 Typeset">

    /**
     * 排版
     */
    open var typeset: FormTypeset? = null

    /**
     * 独占一行
     */
    open var loneLine = false

    /**
     * 新的一行
     */
    open var newLine = false

    /**
     * 列宽提供器
     */
    open var columnProvider: FormColumnBlock? = null

    /**
     * 在边缘显示
     */
    open var showAtEdge = true

    /**
     * 空位自动填充
     */
    open var autoFill = false

    /**
     * 填充边缘
     */
    open var fillEdgesPadding: Boolean = true

    //</editor-fold>

    //<editor-fold desc="交互 Interactive">

    /**
     * 是否启用点击事件
     */
    open var isEnabledItemClick: Boolean = false

    /**
     * 点击事件
     */
    open var onClickListener: FormOnItemClickListener? = null

    fun onClickListener(block: FormOnItemClickListener?) {
        onClickListener = block
    }

    //</editor-fold>

//    //<editor-fold desc="联动 Linkage">
//
//    /**
//     * 联动代码块
//     */
//    var linkageBlock: FormLinkageBlock? = null
//        private set
//
//    /**
//     * 设置联动
//     */
//    fun setLinkage(block: FormLinkageBlock?) {
//        linkageBlock = block
//    }
//
//    /**
//     * 触发联动操作
//     */
//    fun triggerLinkage(part: AbstractPart<*>) {
//        linkageBlock?.invoke(FormLinkage(part))
//    }
//
//    //</editor-fold>
//
//
//    //<editor-fold desc="验证 Validate">
//
//    /**
//     * 数据验证模式
//     */
//    open var validateMode: FormValidateMode = FormValidateMode.OUTPUT
//
//    /**
//     * 执行数据验证
//     */
//    @Throws(FormDataVerificationException::class)
//    fun executeDataVerification(adapterEnabled: Boolean) {
//        when (validateMode) {
//            FormValidateMode.ALWAYS -> Unit
//            FormValidateMode.OUTPUT -> if (!isOutput(adapterEnabled)) return
//            FormValidateMode.NEVER -> return
//        }
//        FormManager.findItemDataActuator(javaClass)?.also {
//            if (it.isOverwriteOriginalDataVerification()) {
//                it.dataVerification(this)
//                return
//            }
//        }
//        dataVerification()
//    }
//
//    /**
//     * 数据验证
//     */
//    @Throws(FormDataVerificationException::class)
//    protected open fun dataVerification() {
//        if (required && content == null) {
//            throw FormDataVerificationException(this, FormDataVerificationException.ErrorType.Empty)
//        }
//    }
//
//    //</editor-fold>
//
//    //<editor-fold desc="输出 Output">
//
//    /**
//     * 输出模式
//     */
//    open var outputMode: FormOutputMode = FormOutputMode.VISIBLE
//
//    /**
//     * 是否输出
//     */
//    fun isOutput(adapterEnabled: Boolean) = when (outputMode) {
//        FormOutputMode.ALWAYS -> true
//        FormOutputMode.VISIBLE -> isVisible(adapterEnabled)
//        FormOutputMode.VISIBLE_ENABLED -> isVisible(adapterEnabled) && isEnable(adapterEnabled)
//        FormOutputMode.VISIBLE_DISABLE -> isVisible(adapterEnabled) && !isEnable(adapterEnabled)
//        FormOutputMode.INVISIBLE -> !isVisible(adapterEnabled)
//        FormOutputMode.INVISIBLE_ENABLE -> !isVisible(adapterEnabled) && isEnable(adapterEnabled)
//        FormOutputMode.INVISIBLE_DISABLE -> !isVisible(adapterEnabled) && !isEnable(adapterEnabled)
//        FormOutputMode.ENABLED -> isEnable(adapterEnabled)
//        FormOutputMode.DISABLE -> !isEnable(adapterEnabled)
//        FormOutputMode.NEVER -> false
//    }
//
//    /**
//     * 执行输出
//     */
//    fun executeOutput(adapterEnabled: Boolean, json: JSONObject) {
//        if (!isOutput(adapterEnabled)) return
//        val actuator = FormManager.findItemDataActuator(javaClass)
//        if (actuator?.isOverwriteOriginalOutputData() == true) {
//            actuator.outputData(this, json)
//        } else outputData(json)
//        if (actuator?.isOverwriteOriginalOutputExtensionData() == true) {
//            actuator.outputExtensionData(this, json)
//        } else outputExtensionData(json)
//    }
//
//    /**
//     * 输出数据
//     */
//    protected open fun outputData(json: JSONObject) {
//        if (field != null && content != null) json.put(field!!, content)
//    }
//
//    /**
//     * 输出扩展数据
//     */
//    protected open fun outputExtensionData(json: JSONObject) {
//        extensionContent.forEach { field, content -> json.putOpt(field, content) }
//    }
//
//    //</editor-fold>

    //<editor-fold desc="内部 Internal">

    /**
     * 真实的启用状态
     */
    var isEnabled: Boolean = true
        internal set

    /**
     * 真实的菜单显示状态
     */
    var isMenuVisible: Boolean = true
        internal set

    /**
     * 真实的菜单启用状态
     */
    var isMenuEnabled: Boolean = true
        internal set

    /**
     * 边界信息
     */
    var boundary: FormBoundary = FormBoundary()

    /**
     * 全局坐标
     */
    var globalPosition: Int = -1
        internal set

    var localPosition: Int = -1
        internal set

    var groupCount = -1
        internal set

    var groupIndex = -1
        internal set

    var countInGroup = -1
        internal set

    var positionInGroup = -1
        internal set

    var columnCount: Int = -1
        internal set

    var columnIndex: Int = -1
        internal set

    var columnSize: Int = -1
        internal set

    internal var lastEnabled: Boolean? = null

    internal var lastMenuVisible: Boolean? = null

    var lastBoundary: FormBoundary? = null
        internal set

    @CallSuper
    open fun resetInternalData() {
        lastEnabled = isEnabled
        lastMenuVisible = isMenuVisible
        lastBoundary = boundary
        groupCount = -1
        groupIndex = -1
        countInGroup = -1
        positionInGroup = -1
    }

    //</editor-fold>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractFormItem<*>) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}