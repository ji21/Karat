package com.example.karat.activities

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.karat.Global
import com.example.karat.R
import com.example.karat.adapters.ViewPagerAdaptor
import com.example.karat.databinding.ActivityMainBinding
import com.example.karat.fragments.ChatsFragment
import com.example.karat.fragments.MainFragment
import com.example.karat.fragments.NewsFragment
import com.example.karat.utils.FadeInPageTransformer
import com.example.karat.viewmodel.NotificationViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var model : NotificationViewModel
    private var opened = false
    private var profile : MenuItem? = null
    private val g = Global()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(com.example.karat.viewmodel.NotificationViewModel::class.java)
        configureTopAppBar()
        configureBottomNav()
        configureNavDrawer()
    }


    @SuppressLint("ServiceCast")
    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar, menu)

        val searchMenuItem = menu.findItem(R.id.search)
        val profileMenuItem = menu.findItem(R.id.profile)
        val bellMenuItem = menu.findItem(R.id.bell)
        val searchView = searchMenuItem?.actionView as SearchView

        hideSearchHintIcon(searchView)
        hideSearchUnderline(searchView)
        searchMenuItem.setOnActionExpandListener(object :
                    MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                    profileMenuItem.setVisible(false)
                    bellMenuItem.setVisible(false)
                    searchMenuItem.setVisible(false)
                    searchView.queryHint = "Search for friends"
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    invalidateOptionsMenu()
                    hideKeyboard()
                    searchView.onActionViewCollapsed()
                    return true
                }
            })
//
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onPrepareOptionsMenu(menu: Menu) : Boolean {
//        val bellMenuItem = menu.findItem(R.id.bell)
//
//        val testObserver = Observer<String> {
//            bellMenuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bell_active))
//
//            val a = RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
//
//            bellMenuItem.actionView.startAnimation(a)
//        }
//
//        model.test.observe(this, testObserver)
//        return super.onPrepareOptionsMenu(menu)
//    }


    private fun hideSearchHintIcon(searchView: SearchView) {
        searchView.setIconifiedByDefault(false)
        val searchViewIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchViewIcon.setVisibility(View.GONE)
        searchViewIcon.setImageDrawable(null)
    }

    private fun hideSearchUnderline(searchView: SearchView) {
        val searchBackgroundView = searchView.findViewById(androidx.appcompat.R.id.search_plate) as View
        searchBackgroundView.background = null
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
                item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bell))
                return true
            }
//            R.id.search -> {
//                val intent = Intent(this, SearchableActivity::class.java)
//                startActivity(intent)
//            }
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
                when (position) {
                    0 -> botNav.selectedItemId = R.id.news_view
                    1 -> botNav.selectedItemId = R.id.mains_view
                    2 -> botNav.selectedItemId = R.id.chats_view
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