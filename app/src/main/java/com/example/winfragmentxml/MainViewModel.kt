package com.example.winfragmentxml

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val message = MutableLiveData("This is default data binding message")

    fun setMessage(text: String) {
        message.value = text
    }
}