package com.example.karat.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.karat.R
import com.example.karat.databinding.ActivityMainBinding
import com.example.karat.databinding.AuthenticationMainBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: AuthenticationMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("wejkfhbkwifghewkieflgdkhberkifkghs")
        binding = AuthenticationMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        try {
//            binding = AuthenticationMainBinding.inflate(layoutInflater)
//            setContentView(binding.root)
//        } catch (ex: Exception) {
//            println(ex)
//        }
        configureTopAppBar()
//
//        val finalHost = NavHostFragment.create(R.navigation.authentication_navigation)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.nav_host, finalHost)
//            .setPrimaryNavigationFragment(finalHost) // equivalent to app:defaultNavHost="true"
//            .commit()

    }

    private fun configureTopAppBar() {
        setSupportActionBar(binding.myToolBar)
    }
}