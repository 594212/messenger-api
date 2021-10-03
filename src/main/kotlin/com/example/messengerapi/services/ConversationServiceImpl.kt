package com.example.messengerapi.services

import com.example.messengerapi.exceptions.ConversationInvalidException
import com.example.messengerapi.models.Conversation
import com.example.messengerapi.models.User
import com.example.messengerapi.repositaries.ConversationRepository
import org.springframework.stereotype.Service
import kotlin.jvm.Throws


@Service
class ConversationServiceImpl(val repository: ConversationRepository):ConversationService {

    override fun createConversation(userA: User, userB: User): Conversation {
        val conversation = Conversation(userA,userB)
        repository.save(conversation)
        return  conversation
    }

    override fun conversationExists(userA: User, userB: User): Boolean {
        return if (repository.findBySenderIdAndRecipientId(userA.id, userB.id) != null)
            true
        else
            repository.findBySenderIdAndRecipientId(userB.id, userA.id) != null
    }


    override fun getConversation(userA: User, userB: User): Conversation? {
        return when {
            repository.findBySenderIdAndRecipientId(userA.id, userB.id) != null ->
                repository.findBySenderIdAndRecipientId(userA.id, userB.id)

            repository.findBySenderIdAndRecipientId(userB.id, userA.id) != null ->
                repository.findBySenderIdAndRecipientId(userB.id,userA.id)
            else -> null
        }
    }

    @Throws(ConversationInvalidException::class)
    override fun retrieveThread(conversationId: Long): Conversation {
        val conversation = repository.findById(conversationId)
        if(!conversation.isPresent) throw ConversationInvalidException("Invalid conversation id '$conversationId'")
        return conversation.get()
    }

    override fun listUserConversation(userId: Long): ArrayList<Conversation> {
        val conversationList: ArrayList<Conversation> = ArrayList()
        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll((repository.findByRecipientId(userId)))

        return conversationList
    }

    override fun    nameSecondParty(conversation: Conversation, userId: Long): String {
        return if(conversation.sender?.id==userId)
            conversation.recipient?.username as String
        else
            conversation.sender?.username as String
    }
}