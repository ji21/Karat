package com.example.karat

import android.app.Application
import com.example.karat.networkrequests.WebSocketSingleton
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class Global : Application() {
    val domain = "10.214.131.89"
    val host = "http://$domain:8000/"
    val webSocket = buildWebSocket()

    private fun buildWebSocket(): WebSocket {
        val client = OkHttpClient.Builder().readTimeout(0, TimeUnit.SECONDS).build()
        val request = Request.Builder().url("ws://$domain:8000/ws/chat/lobby/").build()
        val wsListener = WebSocketSingleton()
        println("ejgrhjbwkelkfioruhjwgbmnkfldoiuehjw")
        return client.newWebSocket(request, wsListener)
    }

}
