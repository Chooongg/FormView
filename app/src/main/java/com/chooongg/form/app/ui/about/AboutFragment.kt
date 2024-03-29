package com.chooongg.form.app.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chooongg.android.ktx.attrColor
import com.chooongg.form.app.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.background = null
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://github.com/Chooongg/FormAdapter2")
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.statusBarColor =
            requireContext().attrColor(com.google.android.material.R.attr.colorSurface)
    }
}