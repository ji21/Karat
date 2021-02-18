package com.example.karat.utils

import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import androidx.navigation.findNavController
import com.example.karat.R

class MyOnTouchListener : View.OnTouchListener {
    override fun onTouch(view: View, event: MotionEvent) : Boolean {
        when(event.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                println("action down")
                view.setBackgroundColor(Color.parseColor("#ededed"))
            }
            MotionEvent.ACTION_MOVE -> {
                view.setBackgroundColor(Color.parseColor("#ffffff"))
            }
            MotionEvent.ACTION_UP -> {
                println("action up")
                view.setBackgroundColor(Color.parseColor("#ffffff"))
                view.findNavController().navigate(R.id.conversationFragment)
            }
        }
        return true
    }
}