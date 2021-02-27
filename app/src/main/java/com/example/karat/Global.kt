package com.example.karat

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class Global : Application() {
    val domain = "10.214.131.89"
    val host = "http://$domain:8000/"
}
