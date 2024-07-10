package com.chooongg.formView.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chooongg.formView.app.R
import com.chooongg.formView.app.databinding.FragmentHomeBinding
import com.chooongg.formView.config.FormIndependentItemConfig
import com.chooongg.formView.enum.FormVisibilityMode
import com.chooongg.formView.style.FormCardM3Style
import com.chooongg.formView.style.FormCardStrokeStyle
import com.chooongg.formView.text
import com.chooongg.formView.typeset.FormNoneTypeset

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        binding.formView.setData {
            addPart(FormCardStrokeStyle()) {
                text("文本", "field", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "field", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
            }
            addPart(FormCardStrokeStyle(FormIndependentItemConfig())) {
                columnProvider = { if (it == 1) 2 else it }
                text("文本", "field", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "field", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                for (i in 0..5) {
                    text("文本", "field", "测试12312312312345")
                }
            }
            addPart(FormCardM3Style()) {
                text("文本", "field", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "field", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
            }
            addPart(FormCardM3Style()) {
                text("文本", "field", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "field", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
            }
            addPart(FormCardM3Style()) {
                text("文本", "field", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "field", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
            }
            addPart {
                text("文本", "field", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                    typeset = FormNoneTypeset()
                }
                text("文本", "field", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "field", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
            }
            addPart {
                text("文本", "field", "测试") {
                    visibilityMode = FormVisibilityMode.DISABLED
                    typeset = FormNoneTypeset()
                }
                text("文本", "field", "测试") {
                    loneLine = true
                    menu = R.menu.main
                }
                text("文本", "field", "测试") {
                    menu = R.menu.item
                    visibilityMode = FormVisibilityMode.DISABLED
                }
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
            }
        }
    }
}