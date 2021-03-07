package com.example.karat

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.example.karat.activities.AuthenticationActivity
import com.example.karat.activities.MainActivity
import com.example.karat.utils.UserSharedPreferences

class SessionManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userSharedPreferences = UserSharedPreferences(this)
        if (userSharedPreferences.isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, AuthenticationActivity::class.java)
            userSharedPreferences.setLoggedIn(true)
            startActivity(intent)
        }
        finish()
    }
}
