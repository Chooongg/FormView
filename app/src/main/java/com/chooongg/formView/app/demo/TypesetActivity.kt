package com.chooongg.formView.app.demo

import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.chooongg.formView.action
import com.chooongg.formView.app.R
import com.chooongg.formView.button
import com.chooongg.formView.data.FormData
import com.chooongg.formView.divider
import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.label
import com.chooongg.formView.style.FormCardElevatedStyle
import com.chooongg.formView.text
import com.chooongg.formView.tip

class TypesetActivity : AbstractDemoActivity(R.string.demo_typeset) {

    private val model by viewModels<TypesetViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.formView.setData(model.data)
    }

    class TypesetViewModel : ViewModel() {
        val data = FormData(style = FormCardElevatedStyle()) {
            part {
                name = "action { ... }"
                action("Version", "version", "1.0.0\n123\n4243") {
                    badge = "10"
                }
            }
            part(column = FormColumn { it + 1 }) {
                name = "button { ... }"
                button("Normal", null)
                button("Custom Style", null) {
                    buttonStyle =
                        com.google.android.material.R.style.Widget_Material3_Button_TextButton
                }
                button("Gravity Start", null) {
                    contentGravity = FormGravity(Gravity.START)
                }
                button("Gravity End", null) {
                    contentGravity = FormGravity(Gravity.END)
                }
                button("Gravity Center", null) {
                    contentGravity = FormGravity(Gravity.CENTER)
                }
            }
            part {
                name = "divider { ... }"
                divider()
                tip("↑ Normal Divider")
                divider {
                    insetStartFromPadding = true
                    insetEndFromPadding = true
                }
                tip("↑ Inset From Padding")
                divider {
                    insetStartResId = R.dimen.custom_divider_inset
                    insetEndResId = R.dimen.custom_divider_inset
                }
                tip("↑ Inset From ResId")
            }
            part {
                name = "label { ... }"
                label("This Is Label")
                label("Gravity Center") {
                    nameGravity = FormGravity(Gravity.CENTER)
                }
                label("Custom Text Appearance") {
                    nameTextAppearance = R.style.Form_TextAppearance_Label_Custom
                }
            }
            part {
                name = "text { ... }"
                text("Name", "Content")
            }
            part {
                name = "Tip { ... }"
                tip("This Is Tip")
            }
        }
    }
}