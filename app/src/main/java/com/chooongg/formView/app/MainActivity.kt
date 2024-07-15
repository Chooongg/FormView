package com.chooongg.formView.app

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chooongg.formView.app.databinding.ActivityMainBinding
import com.chooongg.formView.app.fragment.AboutFragment
import com.chooongg.formView.app.fragment.ComponentsFragment
import com.chooongg.formView.app.fragment.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val fragments = arrayListOf(
        HomeFragment(), AboutFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPager.offscreenPageLimit = 999
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = Adapter(this, fragments)
        with(binding.navigationView as NavigationBarView) {
            onFragmentSelected(selectedItemId)
            setOnItemSelectedListener(this@MainActivity)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return onFragmentSelected(item.itemId)
    }

    private fun onFragmentSelected(itemId: Int): Boolean {
        when (itemId) {
            R.id.nav_home -> binding.viewPager.setCurrentItem(0, false)
            R.id.nav_components -> binding.viewPager.setCurrentItem(1, false)
            R.id.nav_about -> binding.viewPager.setCurrentItem(2, false)
            else -> return false
        }
        return true
    }

    class Adapter(activity: AppCompatActivity, private val fragments: List<Fragment>) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}