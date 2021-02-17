package com.example.karat.networkrequests
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okio.ByteString

class WebSocketSingleton : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
//        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
        val tutMap: Map<String, Any> =
                mapOf("message" to "Hi this is the android phone that just connected", "time" to java.util.Calendar.getInstance())

        val jsonTutMap: String = Gson().toJson(tutMap)
        webSocket.send(jsonTutMap)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        output("Receiving : " + text!!)
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

    companion object {
        private val NORMAL_CLOSURE_STATUS = 1000
    }

    private fun output(txt: String) {
        Log.v("WSS", txt)
    }
}