package com.example.karat.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.karat.Global
import com.google.gson.Gson
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit

class NotificationViewModel : ViewModel() {

    private val g = Global()
    private val domain = g.domain
    val webSocket = buildWebSocket()

    val conversations : MutableLiveData<MutableMap<String, MutableMap<String, String>>> by lazy {
        MutableLiveData<MutableMap<String, MutableMap<String, String>>> ()
    }

    val test : MutableLiveData<String> by lazy {
        MutableLiveData<String> ()
    }


    private fun buildWebSocket(): WebSocket {
        println("oierjhkwegihgvhgvhvhggvhghvvghvghvghghv")
        val client = OkHttpClient.Builder().readTimeout(0, TimeUnit.SECONDS).build()
        val request = Request.Builder().url("ws://$domain:8000/ws/chat/user2/").build()
        val wsListener = WebSocketConnection()
        return client.newWebSocket(request, wsListener)
    }

    inner class WebSocketConnection : WebSocketListener() {

        private val NORMAL_CLOSURE_STATUS = 1000

        override fun onOpen(webSocket: WebSocket, response: Response) {
//        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
            val tutMap: Map<String, Any> =
                    mapOf("message" to "Hi this is the android phone that just connected", "time" to java.util.Calendar.getInstance())

            val jsonTutMap: String = Gson().toJson(tutMap)
            webSocket.send(jsonTutMap)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Receiving : " + text!!)
            test.postValue(text)
//        mainActivity.binding.bottom.visibility = GONE
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            output("Receiving bytes : " + bytes!!.hex())
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
            output("Closing : $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error : " + t.message)
        }


        private fun output(txt: String) {
            Log.v("WSS", txt)
        }
    }

}