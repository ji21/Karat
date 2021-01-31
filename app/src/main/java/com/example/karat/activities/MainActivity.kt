package com.example.karat.activities

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.viewpager.widget.ViewPager
import com.example.karat.R
import com.example.karat.adapters.ViewPagerAdaptor
import com.example.karat.databinding.ActivityMainBinding
import com.example.karat.fragments.ChatsFragment
import com.example.karat.fragments.MainFragment
import com.example.karat.fragments.NewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var opened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureBottomNav()
        configureTopAppBar()
        configureNavDrawer()
    }


    private fun configureNavDrawer() {
        binding.drawerLayout.isInvisible = true
    }

    private fun configureBottomNav() {
        val mainFragment = MainFragment()
        val newsFragment = NewsFragment()
        val chatsFragment = ChatsFragment()
        val adapter = ViewPagerAdaptor(supportFragmentManager)
        val viewPager = binding.viewPager
        val botNav = binding.bottom
        adapter.addFragment(newsFragment)
        adapter.addFragment(mainFragment)
        adapter.addFragment(chatsFragment)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(1)
        botNav.selectedItemId = R.id.mains_view
//
        botNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mains_view -> viewPager.setCurrentItem(1, false)
                R.id.news_view -> viewPager.setCurrentItem(0, false)
                R.id.chats_view -> viewPager.setCurrentItem(2, false)
            }
            true
        }

        viewPager?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                println(position.toString())
                when(position) {
                    0-> botNav.selectedItemId = R.id.news_view
                    1-> botNav.selectedItemId = R.id.mains_view
                    2 -> botNav.selectedItemId = R.id.chats_view
                }
            }
        })
        
    }


    private fun configureTopAppBar() {
        binding.profile.setBackgroundResource(R.drawable.ic_placeholder)
        binding.search.setBackgroundResource(R.drawable.ic_search)
    }

    fun openCloseNavigationDrawer(view: View) {
        val offset = binding.drawerLayout.width.toFloat()
        if (opened) {
            binding.profile.alpha = 1.0f
            binding.drawerLayout.isInvisible = true
            moveRootView(0f)
        } else {
            binding.profile.alpha = 0f
            animateOpenDrawer()
            moveRootView(-offset)
        }
        opened = !opened
    }

    fun presentSearchView(view: View) {

    }

    private fun animateOpenDrawer() {
        val drawer = binding.drawerLayout
        val profile = binding.profile
        drawer.isInvisible = false
        drawer.alpha = 0f
        drawer.animate().duration = 500
        drawer.animate().alpha(1f).start()

    }

    private fun moveRootView(offset: Float) {
        ObjectAnimator.ofFloat(binding.screen, "translationX", offset).apply {
            duration = 100
            start()
        }
    }

}