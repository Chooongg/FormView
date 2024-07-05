package com.chooongg.formView.item

import androidx.annotation.DrawableRes
import androidx.annotation.GravityInt
import com.chooongg.formView.FormColorStateListBlock
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.data.FormExtensionMap
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.typeset.AbstractFormTypeset
import com.google.android.material.button.MaterialButton
import kotlin.reflect.KClass

abstract class BaseForm<CONTENT>(
    /**
     * 名称
     */
    var name: Any?,
    /**
     * 字段
     */
    var field: String?,
    /**
     * 内容
     */
    var content: CONTENT?
) : AbstractForm() {

    //<editor-fold desc="基础 Basic">

    /**
     * 获取提供程序
     */
    abstract fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider>

    @DrawableRes
    override var icon: Int? = null

    override var iconTint: FormColorStateListBlock? = null

    @MaterialButton.IconGravity
    override var iconGravity: Int? = null

    override var iconSize: Int? = null

    /**
     * 提示
     */
    var hint: Any? = null

    /**
     * 扩展内容
     */
    val extensionContent = FormExtensionMap()

    /**
     * 是否为必填项
     */
    var required: Boolean = false

    /**
     * 获取内容文本
     */
    open fun getContentText(): CharSequence? {
        return content.toString()
    }

    //</editor-fold>

    //<editor-fold desc="排版 Typeset">

    /**
     * 排版
     */
    open var typeset: AbstractFormTypeset? = null

    /**
     * 独占一行
     */
    open var loneLine = false

    /**
     * 在边缘显示
     */
    open var showAtEdge = true

    /**
     * 自动填充
     */
    open var autoFill = false

    /**
     * 重力
     */
    @GravityInt
    open var gravity: Int? = null

    /**
     * 内容重力
     */
    open var contentGravity: FormContentGravity? = null

//    /**
//     * 填充边缘
//     */
//    open var fillEdges: Boolean = true
//
//    @GravityInt
//    open fun getContentGravity(holder: FormItemViewHolder, isMultiColumn: Boolean): Int {
//        return if (isMultiColumn) {
//            contentGravity?.multiColumnGravity ?: gravity
//            ?: holder.layout.contentGravity?.multiColumnGravity
//            ?: holder.style.config.contentGravity.multiColumnGravity
//        } else {
//            contentGravity?.gravity ?: gravity ?: holder.layout.contentGravity?.gravity
//            ?: holder.style.config.contentGravity.gravity
//        }
//    }
//
//    //</editor-fold>
//
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
//    //<editor-fold desc="交互 Interactive">
//
//    /**
//     * 是否响应点击事件
//     */
//    open var isRespondToClickEvents: Boolean = false
//
//    //</editor-fold>
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
    var enabled: Boolean? = null
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

    var lastEnabled: Boolean? = null
        internal set

    var lastBoundary: FormBoundary? = null
        internal set

    open fun resetInternalData() {
        lastEnabled = enabled
        lastBoundary = boundary
        groupCount = -1
        groupIndex = -1
        countInGroup = -1
        positionInGroup = -1
    }

    //</editor-fold>
}