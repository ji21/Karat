package com.example.karat

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.karat.databinding.ActivityMainBinding
import com.example.karat.fragments.ChatsFragment
import com.example.karat.fragments.MainFragment
import com.example.karat.fragments.NewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureBottomNav()
        configureTopAppBar()
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
            replace(R.id.screen, fragment)
            commit()
        }

    private fun configureTopAppBar() {
        binding.profile.setBackgroundResource(R.drawable.ic_placeholder)
        binding.profile.setOnClickListener {
            println("owieurhgjw")
            Log.d("TAG", "message")
        }
        Log.d("TAG", "message")
    }

}