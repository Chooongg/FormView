package com.chooongg.formView.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.chooongg.formView.app.R
import com.chooongg.formView.app.databinding.FragmentFormBinding
import com.chooongg.formView.data.FormData
import com.chooongg.formView.divider
import com.chooongg.formView.enum.FormColumn
import com.chooongg.formView.enum.FormTypeset
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.style.FormCardOutlinedStyle
import com.chooongg.formView.text
import com.chooongg.formView.typeset.FormHorizontalTypeset
import com.chooongg.formView.typeset.FormNoneTypeset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding

    private val model: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentFormBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setTitle(R.string.app_name)
        binding.toolbar.inflateMenu(R.menu.edit)
        binding.toolbar.setOnMenuItemClickListener {
            if (binding.formView.isEnabled) {
                binding.formView.isEnabled = false
                it.setIcon(R.drawable.ic_edit_off)
            } else {
                binding.formView.isEnabled = true
                it.setIcon(R.drawable.ic_edit_on)
            }
            true
        }
        TextViewCompat.getTextMetricsParams(TextView(requireContext()))
        binding.formView.setData(model.data)
    }

    class HomeViewModel : ViewModel() {
        val data = FormData {
            typeset = FormTypeset { formColumn, itemColumn ->
                FormHorizontalTypeset::class
            }
//            part(FormCardOutlinedStyle()) {
//                name = "NormalPart"
//                text("文本", "测试") {
//                    visibilityMode = FormVisibilityMode.DISABLED
//                }
//                text("文本", "测试") {
//                    loneLine = true
//                    menu = R.menu.main
//                    typeset = FormTypeset(FormNoneTypeset::class)
//                }
//                divider {
//                    icon = 1
//                }
//                text("文本", "测试") {
//                    menu = R.menu.item
//                    visibilityMode = FormVisibilityMode.DISABLED
//                }
//                text("文本", "测试")
//                text("文本", "测试")
//                text("文本", "测试")
//            }
            part(FormCardOutlinedStyle { isIndependentItem = true }) {
                name = "Independent"
                column = FormColumn { if (it == 1) 2 else it }
                text("文本", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                for (i in 0..5) {
                    text("文本", "测试12312312312345")
                }
            }
//            part(FormCardOutlinedStyle { isIndependentItem = true }) {
//                name = "Independent"
//                column = FormColumn { if (it == 1) 2 else it }
//                text("文本", "测试") {
//                    visibilityMode = FormVisibilityMode.DISABLED
//                }
//                text("文本", "测试") {
//                    loneLine = true
//                    menu = R.menu.main
//                }
//                text("文本", "测试") {
//                    menu = R.menu.item
//                    visibilityMode = FormVisibilityMode.DISABLED
//                }
//                for (i in 0..5) {
//                    text("文本", "测试12312312312345")
//                }
//            }
        }
    }
}