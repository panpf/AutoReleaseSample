package com.github.panpf.auto.release.sample

import android.os.Bundle
import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.github.panpf.auto.release.sample.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.viewPager.apply {
            adapter = MainFragmentAdapter(this@MainActivity)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> binding.bottomNavigationView.selectedItemId = R.id.compose
                        1 -> binding.bottomNavigationView.selectedItemId = R.id.view
                    }
                }
            })
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.compose -> binding.viewPager.currentItem = 0
                R.id.view -> binding.viewPager.currentItem = 1
            }
            true
        }
    }

    class MainFragmentAdapter(
        fragmentActivity: FragmentActivity
    ) : FragmentStateAdapter(fragmentActivity) {

        private val fragments = listOf(ComposeFragment(), ViewFragment())

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}