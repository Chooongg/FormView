package com.chooongg.form.item

import androidx.annotation.DrawableRes
import androidx.annotation.GravityInt
import com.chooongg.form.FormColorStateListBlock
import com.chooongg.form.FormDataVerificationException
import com.chooongg.form.FormLinkageBlock
import com.chooongg.form.FormManager
import com.chooongg.form.boundary.Boundary
import com.chooongg.form.data.FormExtensionMap
import com.chooongg.form.enum.FormContentGravity
import com.chooongg.form.enum.FormOutputMode
import com.chooongg.form.enum.FormValidateMode
import com.chooongg.form.linkage.FormLinkage
import com.chooongg.form.part.AbstractPart
import com.chooongg.form.typeset.AbstractTypeset
import com.google.android.material.button.MaterialButton
import org.json.JSONObject

abstract class BaseForm<CONTENT>(
    /**
     * 名称
     */
    var name: Any?,
    /**
     * 字段
     */
    var field: String?,
) : AbstractForm() {

    //<editor-fold desc="基础 Basic">

    /**
     * 初始化
     */
    open fun initialize() = Unit

    /**
     * 图标
     */
    @DrawableRes
    open var icon: Int? = null

    /**
     * 图标着色
     */
    open var iconTint: FormColorStateListBlock? = null

    /**
     * 图标重力
     */
    @MaterialButton.IconGravity
    open var iconGravity: Int = MaterialButton.ICON_GRAVITY_TEXT_START

    /**
     * 图标大小
     */
    open var iconSize: Int? = null

    /**
     * 提示: Int(StringRes), String, CharSequence
     */
    var hint: Any? = null

    /**
     * 内容
     */
    var content: CONTENT? = null

    /**
     * 扩展内容
     */
    val extensionContent = FormExtensionMap()

    /**
     * 是否为必填项
     */
    var required: Boolean = false

    //</editor-fold>

    //<editor-fold desc="排版 Typeset">

    /**
     * 排版
     */
    open var typeset: AbstractTypeset? = null

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

    /**
     * 填充边缘
     */
    open var fillEdges: Boolean = true

    //</editor-fold>

    //<editor-fold desc="联动 Linkage">

    /**
     * 联动代码块
     */
    var linkageBlock: FormLinkageBlock? = null
        private set

    /**
     * 设置联动
     */
    fun setLinkage(block: FormLinkageBlock?) {
        linkageBlock = block
    }

    /**
     * 触发联动操作
     */
    fun triggerLinkage(part: AbstractPart<*>) {
        linkageBlock?.invoke(FormLinkage(part))
    }

    //</editor-fold>

    //<editor-fold desc="交互 Interactive">

    /**
     * 是否响应点击事件
     */
    open var isRespondToClickEvents: Boolean = false

    //</editor-fold>

    //<editor-fold desc="验证 Validate">

    /**
     * 数据验证模式
     */
    open var validateMode: FormValidateMode = FormValidateMode.OUTPUT

    /**
     * 执行数据验证
     */
    @Throws(FormDataVerificationException::class)
    fun executeDataVerification(adapterEnabled: Boolean) {
        when (validateMode) {
            FormValidateMode.ALWAYS -> Unit
            FormValidateMode.OUTPUT -> if (!isOutput(adapterEnabled)) return
            FormValidateMode.NEVER -> return
        }
        FormManager.findItemDataActuator(javaClass)?.also {
            if (it.isOverwriteOriginalDataVerification()) {
                it.dataVerification(this)
                return
            }
        }
        dataVerification()
    }

    /**
     * 数据验证
     */
    @Throws(FormDataVerificationException::class)
    protected open fun dataVerification() {
        if (required && content == null) {
            throw FormDataVerificationException(this, FormDataVerificationException.ErrorType.Empty)
        }
    }

    //</editor-fold>

    //<editor-fold desc="输出 Output">

    /**
     * 输出模式
     */
    open var outputMode: FormOutputMode = FormOutputMode.VISIBLE

    /**
     * 是否输出
     */
    fun isOutput(adapterEnabled: Boolean) = when (outputMode) {
        FormOutputMode.ALWAYS -> true
        FormOutputMode.VISIBLE -> isVisible(adapterEnabled)
        FormOutputMode.VISIBLE_ENABLED -> isVisible(adapterEnabled) && isEnable(adapterEnabled)
        FormOutputMode.VISIBLE_DISABLE -> isVisible(adapterEnabled) && !isEnable(adapterEnabled)
        FormOutputMode.INVISIBLE -> !isVisible(adapterEnabled)
        FormOutputMode.INVISIBLE_ENABLE -> !isVisible(adapterEnabled) && isEnable(adapterEnabled)
        FormOutputMode.INVISIBLE_DISABLE -> !isVisible(adapterEnabled) && !isEnable(adapterEnabled)
        FormOutputMode.ENABLED -> isEnable(adapterEnabled)
        FormOutputMode.DISABLE -> !isEnable(adapterEnabled)
        FormOutputMode.NEVER -> false
    }

    /**
     * 执行输出
     */
    fun executeOutput(adapterEnabled: Boolean, json: JSONObject) {
        if (!isOutput(adapterEnabled)) return
        val actuator = FormManager.findItemDataActuator(javaClass)
        if (actuator?.isOverwriteOriginalOutputData() == true) {
            actuator.outputData(this, json)
        } else outputData(json)
        if (actuator?.isOverwriteOriginalOutputExtensionData() == true) {
            actuator.outputExtensionData(this, json)
        } else outputExtensionData(json)
    }

    /**
     * 输出数据
     */
    protected open fun outputData(json: JSONObject) {
        if (field != null && content != null) json.put(field!!, content)
    }

    /**
     * 输出扩展数据
     */
    protected open fun outputExtensionData(json: JSONObject) {
        extensionContent.forEach { field, content -> json.putOpt(field, content) }
    }

    //</editor-fold>

    //<editor-fold desc="内部 Internal">

    /**
     * 真实的启用状态
     */
    internal var enabled: Boolean? = null

    /**
     * 边界信息
     */
    var boundary: Boundary = Boundary()

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

    var spanIndex: Int = -1
        internal set

    var spanSize: Int = -1
        internal set

    var lastEnabled: Boolean? = null
        internal set

    var lastBoundary: Boundary? = null
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