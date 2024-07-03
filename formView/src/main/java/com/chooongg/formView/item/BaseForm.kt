package com.chooongg.formView.item

import androidx.annotation.DrawableRes
import androidx.annotation.GravityInt
import com.chooongg.formView.FormColorStateListBlock
import com.chooongg.formView.FormLinkageBlock
import com.chooongg.formView.data.FormExtensionMap
import com.chooongg.formView.enum.FormContentGravity
import com.chooongg.formView.enum.FormOutputMode
import com.chooongg.formView.enum.FormValidateMode
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.layout.AbstractFormTypeset
import com.google.android.material.button.MaterialButton
import org.json.JSONObject

class BaseForm<CONTENT>(
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

    /**
     * 填充边缘
     */
    open var fillEdges: Boolean = true

    @GravityInt
    open fun getContentGravity(holder: FormItemViewHolder, isMultiColumn: Boolean): Int {
        return if (isMultiColumn) {
            contentGravity?.multiColumnGravity ?: gravity
            ?: holder.layout.contentGravity?.multiColumnGravity
            ?: holder.style.config.contentGravity.multiColumnGravity
        } else {
            contentGravity?.gravity ?: gravity ?: holder.layout.contentGravity?.gravity
            ?: holder.style.config.contentGravity.gravity
        }
    }

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
}