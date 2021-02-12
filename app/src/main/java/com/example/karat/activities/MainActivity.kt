package com.example.karat.activities

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.graphics.alpha
import androidx.core.view.isInvisible
import androidx.viewpager.widget.ViewPager
import com.example.karat.R
import com.example.karat.adapters.ViewPagerAdaptor
import com.example.karat.databinding.ActivityMainBinding
import com.example.karat.fragments.ChatsFragment
import com.example.karat.fragments.MainFragment
import com.example.karat.fragments.NewsFragment
import com.example.karat.utils.FadeInPageTransformer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var opened = false
    private var profile : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureTopAppBar()
        configureBottomNav()
        configureNavDrawer()
    }


    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.profile -> {
                profile = item
                openCloseNavigationDrawer()
                return true
            }
            R.id.search -> {
                println("okok")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun configureNavDrawer() {
        val dummyScreen = binding.dummyScreen
        binding.drawerLayout.isInvisible = true
        dummyScreen.isInvisible = true
        dummyScreen.setOnClickListener {
            openCloseNavigationDrawer()
        }
        dummyScreen.bringToFront()
    }

    private fun configureBottomNav() {
        val mainFragment = MainFragment()
        val newsFragment = NewsFragment()
        val chatsFragment = ChatsFragment()
        val adapter = ViewPagerAdaptor(supportFragmentManager)
        val viewPager = binding.viewPager
        val botNav = binding.bottom

//        val params = binding.spaceTaker.layoutParams
//        params.height = botNav.height
//        binding.spaceTaker.layoutParams = params


        adapter.addFragment(newsFragment)
        adapter.addFragment(mainFragment)
        adapter.addFragment(chatsFragment)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(1)
        viewPager.setPageTransformer(true, FadeInPageTransformer())
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

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0-> botNav.selectedItemId = R.id.news_view
                    1-> botNav.selectedItemId = R.id.mains_view
                    2-> botNav.selectedItemId = R.id.chats_view
                }
            }
        })
        
    }


    private fun configureTopAppBar() {
        setSupportActionBar(binding.myToolBar)
    }

    private fun openCloseNavigationDrawer() {
        val offset = binding.drawerLayout.width.toFloat()
        if (opened){
            binding.drawerLayout.isInvisible = true
            binding.dummyScreen.isInvisible = true
            moveRootView(0f)
            profile?.isVisible = true
        } else {
            animateOpenDrawer()
            moveRootView(-offset)
            moveDummyView(-offset)
            profile?.isVisible = false
        }
        opened = !opened
    }

    fun presentSearchView(view: View) {

    }

    private fun animateOpenDrawer() {
        val drawer = binding.drawerLayout
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

    private fun moveDummyView(offset: Float) {
        val dummyScreen = binding.dummyScreen
        dummyScreen.isInvisible = false
        ObjectAnimator.ofFloat(dummyScreen, "translationX", offset).apply {
            duration = 0
            start()
        }
    }

}