package com.chooongg.formView.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.chooongg.formView.action
import com.chooongg.formView.app.databinding.ActivityMainBinding
import com.chooongg.formView.app.demo.BasicParametersActivity
import com.chooongg.formView.app.demo.ComponentsActivity
import com.chooongg.formView.data.FormData
import com.chooongg.formView.style.FormCardStyle
import com.chooongg.ktx.showToast

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val model by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.appBarLayout.addLiftOnScrollListener { _, backgroundColor ->
            window.statusBarColor = backgroundColor
        }
        binding.formView.setData(model.data)
        binding.formView.setOnItemClickListener { view, part, item ->
            when (item.name) {
                R.string.demo_basic -> startActivity(
                    Intent(this, BasicParametersActivity::class.java)
                )

                R.string.demo_components -> startActivity(
                    Intent(this, ComponentsActivity::class.java)
                )

                else -> showToast("Un supported")
            }
        }
    }

    class MainViewModel : ViewModel() {
        val data: FormData = FormData(
            style = FormCardStyle()
        ) {
            part {
                action(R.string.demo_basic)
                action(R.string.demo_style)
                action(R.string.demo_typeset)
                action(R.string.demo_components)
            }
        }
    }
}