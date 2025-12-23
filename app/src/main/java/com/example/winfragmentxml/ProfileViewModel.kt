package com.example.winfragmentxml

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private val _privacyMessage = MutableStateFlow("")
    val privacyMessage = _privacyMessage.asStateFlow()

    fun updatePrivacyMessage(message: String) {
        _privacyMessage.value = message
    }
}