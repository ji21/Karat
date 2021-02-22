package com.example.karat.activities

import android.animation.ObjectAnimator
import android.app.SearchManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.core.graphics.alpha
import androidx.core.view.isInvisible
import androidx.viewpager.widget.ViewPager
import com.example.karat.Global
import com.example.karat.R
import com.example.karat.adapters.ViewPagerAdaptor
import com.example.karat.databinding.ActivityMainBinding
import com.example.karat.fragments.ChatsFragment
import com.example.karat.fragments.MainFragment
import com.example.karat.fragments.NewsFragment
import com.example.karat.networkrequests.WebSocketSingleton
import com.example.karat.utils.FadeInPageTransformer
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var opened = false
    private var profile : MenuItem? = null
    private val g = Global()

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

//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        (menu.findItem(R.id.search).actionView as SearchView).apply {
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//            isIconifiedByDefault = false
//            isIconified = false
//            onActionViewExpanded()
//        }

        val searchMenuItem = menu.findItem(R.id.search)

        val searchView = menu.findItem(R.id.search).actionView as SearchView

        val profileMenuItem = menu.findItem(R.id.profile)
        val bellMenuItem = menu.findItem(R.id.bell)


        if (searchMenuItem is MenuItem) {
            searchMenuItem.setOnActionExpandListener(object :
                    MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
//                    searchView.isIconified = false
                    profileMenuItem.setVisible(false)
                    bellMenuItem.setVisible(false)
                    searchMenuItem.setVisible(false)
                    searchView.onActionViewExpanded()
                    searchView.setIconified(false)
                    searchView.queryHint = "OMG FUCK THIS SHIT"
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    invalidateOptionsMenu()
                    hideKeyboard()
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }


    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.attributes.token, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.profile -> {
                profile = item
                openCloseNavigationDrawer()
                return true
            }
            R.id.bell -> {
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
                R.id.mains_view -> viewPager.setCurrentItem(1, true)
                R.id.news_view -> viewPager.setCurrentItem(0, true)
                R.id.chats_view -> viewPager.setCurrentItem(2, true)
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