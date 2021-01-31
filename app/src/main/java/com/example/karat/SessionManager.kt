package com.example.karat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.karat.activities.AuthenticationActivity
import com.example.karat.activities.MainActivity

class SessionManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (false) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
        }
        finish()
    }

}