package com.example.karat.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.karat.databinding.AuthenticationMainBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: AuthenticationMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthenticationMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureTopAppBar()
    }

    private fun configureTopAppBar() {
        setSupportActionBar(binding.myToolBar)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        super.onOptionsItemSelected(item)
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        hideKeyboard()
        return super.onTouchEvent(event)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        if (currentFocus != null) currentFocus?.clearFocus()
    }

}