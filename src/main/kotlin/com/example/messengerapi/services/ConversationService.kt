package com.example.messengerapi.services

import com.example.messengerapi.models.Conversation
import com.example.messengerapi.models.User

interface ConversationService {
    fun createConversation(userA: User, userB: User): Conversation
    fun conversationExists(userA: User, userB: User): Boolean
    fun getConversation(userA: User, userB: User): Conversation?
    fun retrieveThread(conversationId: Long): Conversation
    fun listUserConversation(userId: Long): List<Conversation>
    fun nameSecondParty(conversation: Conversation, userId: Long): String



}
