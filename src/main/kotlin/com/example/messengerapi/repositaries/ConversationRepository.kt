package com.example.messengerapi.repositaries

import com.example.messengerapi.models.Conversation
import org.springframework.data.repository.CrudRepository

interface ConversationRepository: CrudRepository<Conversation,Long>{
    fun findBySenderId(id: Long): List<Conversation>
    fun findByRecipientId(id: Long): List<Conversation>
    fun findBySenderIdAndRecipientId(senderId: Long, recipientId: Long): Conversation?

}