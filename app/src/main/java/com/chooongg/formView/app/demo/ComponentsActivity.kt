package com.chooongg.formView.app.demo

import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.chooongg.formView.action
import com.chooongg.formView.app.R
import com.chooongg.formView.button
import com.chooongg.formView.data.FormData
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.label
import com.chooongg.formView.style.FormCardElevatedStyle
import com.chooongg.formView.style.FormCardStyle
import com.chooongg.formView.text
import com.chooongg.formView.tip
import com.chooongg.ktx.showToast

class ComponentsActivity : AbstractDemoActivity(R.string.demo_components) {

    private val model by viewModels<ComponentsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.formView.setData(model.data)
    }

    class ComponentsViewModel : ViewModel() {
        val data = FormData {
            style = FormCardElevatedStyle()
            part {
                name = "action { ... }"
                action("Version", "version", "1.0.0\n123\n4243") {
                    badge = "10"
                }
                text("Item", "Name\nGravity\nCenter\nBottom")
            }
            part {
                name = "button { ... }"
                button("Normal", "normal") {
                    loneLine = true
                    onClickListener { view, part, item ->
                        showToast("onClick")
                    }
                }
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