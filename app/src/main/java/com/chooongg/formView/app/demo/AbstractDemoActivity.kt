package com.chooongg.formView.app.demo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.chooongg.formView.app.R
import com.chooongg.formView.app.databinding.ActivityDemoBinding

abstract class AbstractDemoActivity(@StringRes private val titleResId: Int) : AppCompatActivity() {

    protected val binding by lazy { ActivityDemoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setTitle(titleResId)
        binding.appBarLayout.addLiftOnScrollListener { _, backgroundColor ->
            window.statusBarColor = backgroundColor
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.edit) {
            if (binding.formView.isEnabled) {
                binding.formView.isEnabled = false
                item.setIcon(R.drawable.ic_edit_off)
            } else {
                binding.formView.isEnabled = true
                item.setIcon(R.drawable.ic_edit_on)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}