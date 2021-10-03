package com.example.messengerapi.repositaries

import com.example.messengerapi.models.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long> {
    fun findByConversationId(conversationId: Long): List<Message>

}