package com.chooongg.formView.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.lifecycle.ViewModel
import com.chooongg.formView.action
import com.chooongg.formView.app.databinding.ActivityMainBinding
import com.chooongg.formView.data.FormData
import com.chooongg.formView.style.FormCardOutlinedStyle
import com.chooongg.formView.text
import com.google.android.material.shape.MaterialShapeUtils
import com.google.android.material.slider.Slider
import com.google.android.material.tooltip.TooltipDrawable

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val model by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.formView.setData(model.data)

    }

    class MainViewModel : ViewModel() {
        val data: FormData = FormData {
            part(FormCardOutlinedStyle()) {
                action("Basic Parameters")
                action("Style")
                action("Typeset")
                action("Components")
                text("asdfasdf") {
                    isEnabledItemClick = true
                }
                text("asdfasdf") {
                    isEnabledItemClick = true
                }
                text("asdfasdf") {
                    isEnabledItemClick = true
                }
            }
        }
    }
}