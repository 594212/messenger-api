package com.example.messengerapi.services

import com.example.messengerapi.models.Message
import com.example.messengerapi.models.User

interface MessageService {
    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}