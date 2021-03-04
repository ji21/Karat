package com.example.karat.utils

import android.content.Context

class UserSharedPreferences (context: Context) {
    val preferenceName = "UserSharedPreferences"
    val preferenceUserName = "name"
    val preferencePhone = "phone"
    val preferenceLoggedIn = "logged in"


    val preference = context.getSharedPreferences(preferenceName,  Context.MODE_PRIVATE)

    fun getName(): String? {
        return preference.getString(preferenceUserName, "no name set")
    }

    fun setName(name:String) {
        val editor = preference.edit()
        editor.putString(preferenceName, name)
        editor.apply()
    }

    fun isLoggedIn() : Boolean {
        return preference.getBoolean(preferenceLoggedIn, false)
    }

    fun setLoggedIn(login: Boolean) {
        val editor = preference.edit()
        editor.putBoolean(preferenceLoggedIn, login)
        editor.apply()
    }

    fun getPhone() : String? {
        return preference.getString(preferencePhone, "no phone set")
    }

}