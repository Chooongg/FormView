package com.chooongg.formView.app.demo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.chooongg.formView.app.R
import com.chooongg.formView.data.FormData
import com.chooongg.formView.enum.FormEnableMode
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.text
import com.chooongg.formView.tip
import com.chooongg.formView.typeset.FormNoneTypeset

class BasicParametersActivity : AbstractDemoActivity(R.string.demo_basic) {

    private val model by viewModels<BasicParametersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.formView.setData(model.data)
    }

    class BasicParametersViewModel : ViewModel() {
        val data = FormData {
            part {
                visibilityMode = FormVisibilityMode.ALWAYS
                tip(
                    """Part {
                    |    visibilityMode = FormVisibilityMode.ALWAYS
                    |}""".trimMargin()
                )
            }
            part {
                visibilityMode = FormVisibilityMode.ENABLED
                tip(
                    """Part {
                    |    visibilityMode = FormVisibilityMode.ENABLED
                    |}""".trimMargin()
                )
            }
            part {
                visibilityMode = FormVisibilityMode.DISABLED
                tip(
                    """Part {
                    |    visibilityMode = FormVisibilityMode.DISABLED
                    |}""".trimMargin()
                )
            }
            part {
                visibilityMode = FormVisibilityMode.NEVER
                tip(
                    """Part {
                    |    visibilityMode = FormVisibilityMode.NEVER
                    |}""".trimMargin()
                )
            }
            part {
                name = "Visibility Mode"
                tip("visibilityMode = FormVisibilityMode.ALWAYS") {
                    visibilityMode = FormVisibilityMode.ALWAYS
                }
                tip("visibilityMode = FormVisibilityMode.ENABLED") {
                    visibilityMode = FormVisibilityMode.ENABLED
                }
                tip("visibilityMode = FormVisibilityMode.DISABLED") {
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                tip("visibilityMode = FormVisibilityMode.NEVER") {
                    visibilityMode = FormVisibilityMode.NEVER
                }
            }
            part {
                name = "Enable Model"
                tip("enableMode = FormEnableMode.ALWAYS") {
                    enableMode = FormEnableMode.ALWAYS
                }
                tip("enableMode = FormEnableMode.ENABLED") {
                    enableMode = FormEnableMode.ENABLED
                }
                tip("enableMode = FormEnableMode.DISABLED") {
                    enableMode = FormEnableMode.DISABLED
                }
                tip("enableMode = FormEnableMode.NEVER") {
                    enableMode = FormEnableMode.NEVER
                }
            }
            part {
                name = "Required"
                text("Name", "required = false") { required = false }
                text("Name", "required = true") { required = true }
            }
            part(typeset = FormTypeset(FormNoneTypeset::class)) {
                name = "Menu"
                tip("menuVisibility -> visibilityMode")
                tip("menuEnableMode -> enableMode")
                text("Name", "FormVisibilityMode.ALWAYS") {
                    menu = R.menu.menu_demo
                    menuVisibilityMode = FormVisibilityMode.ALWAYS
                }
                text("Name", "FormVisibilityMode.ENABLED") {
                    menu = R.menu.menu_demo
                    menuVisibilityMode = FormVisibilityMode.ENABLED
                }
                text("Name", "FormVisibilityMode.DISABLED") {
                    menu = R.menu.menu_demo
                    menuVisibilityMode = FormVisibilityMode.DISABLED
                }
                text("Name", "FormVisibilityMode.NEVER") {
                    menu = R.menu.menu_demo
                    menuVisibilityMode = FormVisibilityMode.NEVER
                }
                text("Name", "FormEnableMode.ALWAYS") {
                    menu = R.menu.menu_demo
                    menuEnableMode = FormEnableMode.ALWAYS
                }
                text("Name", "FormEnableMode.ENABLED") {
                    menu = R.menu.menu_demo
                    menuEnableMode = FormEnableMode.ENABLED
                }
                text("Name", "FormEnableMode.DISABLED") {
                    menu = R.menu.menu_demo
                    menuEnableMode = FormEnableMode.DISABLED
                }
                text("Name", "FormEnableMode.NEVER") {
                    menu = R.menu.menu_demo
                    menuEnableMode = FormEnableMode.NEVER
                }
            }
        }
    }
}