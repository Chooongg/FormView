package com.chooongg.formView.app.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.chooongg.formView.app.R
import com.chooongg.formView.app.databinding.FragmentFormBinding
import com.chooongg.formView.data.FormData
import com.chooongg.formView.enum.FormGravity
import com.chooongg.formView.label
import com.chooongg.formView.style.FormCardOutlinedStyle
import com.chooongg.formView.text
import com.chooongg.formView.tip

class ComponentsFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding

    private val model by viewModels<ComponentsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentFormBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setTitle(R.string.components)
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

    class ComponentsViewModel : ViewModel() {
        val data = FormData {
            style = FormCardOutlinedStyle()
            part {
                name = "Base Attribute"
                text("Item", "No Required")
                text("Item", "Required") {
                    required = true
                }
                text("Item", "Name No Gravity")
                text("Item", "Name Gravity Start") {
                    nameGravity = FormGravity(Gravity.START)
                }
                text("Item", "Name Gravity End") {
                    nameGravity = FormGravity(Gravity.END)
                }
                text("Item", "Name Gravity Bottom") {
                    nameGravity = FormGravity(Gravity.BOTTOM)
                }
                text("Item", "Name Gravity Center Horizontal") {
                    nameGravity = FormGravity(Gravity.CENTER_HORIZONTAL)
                }
                text("Item", "Name\nGravity\nCenter") {
                    nameGravity = FormGravity(Gravity.CENTER)
                }
                text("Item", "Name\nGravity\nEnd\nBottom") {
                    nameGravity = FormGravity(Gravity.END or Gravity.BOTTOM)
                }
            }
            part {
                name = "label <FormLabel>"
                label("This Is Label")
                label("Gravity Center") {
                    nameGravity = FormGravity(Gravity.CENTER)
                }
                label("Custom Text Appearance") {
                    textAppearance = R.style.Form_TextAppearance_Label_Custom
                }
            }
            part {
                name = "text <FormText>"
                text("Name", "Content")
            }
            part {
                name = "Tip <FormTip>"
                tip("This Is Tip")
            }
        }
    }
}