package com.example.karat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {
    val conversations : MutableLiveData<MutableMap<String, MutableMap<String, String>>> by lazy {
        MutableLiveData<MutableMap<String, MutableMap<String, String>>> ()
    }

    val test : MutableLiveData<String> by lazy {
        MutableLiveData<String> ()
    }
}