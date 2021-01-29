package com.example.karat

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.MotionEventCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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

        makeFragment(mainFragment)
        binding.bottom.selectedItemId = R.id.mains_view

        binding.bottom.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mains_view -> makeFragment(mainFragment)
                R.id.news_view -> makeFragment(newsFragment)
                R.id.chats_view -> makeFragment(chatsFragment)
            }
            true
        }
    }

    private fun makeFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment, fragment)
            commit()
        }

    private fun configureTopAppBar() {
        binding.profile.setBackgroundResource(R.drawable.ic_placeholder)
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