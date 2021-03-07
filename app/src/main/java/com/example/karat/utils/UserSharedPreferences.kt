package com.example.karat.utils

import android.content.Context

class UserSharedPreferences (context: Context) {
    val preferenceName = "UserSharedPreferences"
    val preferenceUserName = "name"
    val preferencePhone = "phone"
    val preferenceLoggedIn = "logged in"
    val preferenceRefreshToken = "refresh token"
    val preferenceAccessToken = "access token"

    val preference = context.getSharedPreferences(preferenceName,  Context.MODE_PRIVATE)

    fun getName(): String? {
        return preference.getString(preferenceUserName, "no name set")
    }

    fun setName(name:String) {
        val editor = preference.edit()
        editor.putString(preferenceName, name)
        editor.apply()
    }

    fun getAccessToken() : String? {
        return preference.getString(preferenceAccessToken, "no access token")
    }

    fun setAccessToken(accessToken: String) {
        val editor = preference.edit()
        editor.putString(preferenceAccessToken, accessToken)
        editor.apply()
    }

    fun getRefreshToken() : String? {
        return preference.getString(preferenceRefreshToken, "no access token")
    }

    fun setRefreshToken(refreshToken: String) {
        val editor = preference.edit()
        editor.putString(preferenceRefreshToken, refreshToken)
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