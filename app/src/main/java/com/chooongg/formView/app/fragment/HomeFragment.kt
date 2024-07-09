package com.chooongg.formView.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chooongg.formView.app.R
import com.chooongg.formView.app.databinding.FragmentHomeBinding
import com.chooongg.formView.text

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
            if (binding.formView.isEnabled){
                binding.formView.isEnabled = false
                it.setIcon(R.drawable.ic_edit_off)
            }else{
                binding.formView.isEnabled = true
                it.setIcon(R.drawable.ic_edit_on)
            }
            true
        }
        binding.formView.setData {
            for (i in 0..4) {
                addPart {
                    text("文本", "field", "测试")
                    text("文本", "field", "测试") {
                        loneLine = true
                        menu = R.menu.main
                    }
                    text("文本", "field", "测试") {
                        menu = R.menu.item
                    }
                    text("文本", "field", "测试")
                    text("文本", "field", "测试")
                    text("文本", "field", "测试")
                }
            }
        }
    }
}