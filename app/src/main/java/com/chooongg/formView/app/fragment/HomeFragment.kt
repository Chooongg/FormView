package com.chooongg.formView.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chooongg.formView.FormView
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
        binding.formView.setData {
            addPart {
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
                text("文本", "field", "测试")
            }
        }
    }
}