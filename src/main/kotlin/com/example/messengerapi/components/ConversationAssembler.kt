package com.example.messengerapi.components

import com.example.messengerapi.helpers.objects.ConversationListVO
import com.example.messengerapi.helpers.objects.ConversationVO
import com.example.messengerapi.helpers.objects.MessageVO
import com.example.messengerapi.models.Conversation
import com.example.messengerapi.services.ConversationServiceImpl
import org.springframework.stereotype.Component


@Component
class ConversationAssembler(val conversationServiceImpl: ConversationServiceImpl,
                            val messageAssembler: MessageAssembler) {

    fun toConversationVO(conversation: Conversation, userId: Long):ConversationVO {
        val conversationMessages: ArrayList<MessageVO> = ArrayList()
        conversation.messages?.mapTo(conversationMessages) { messageAssembler.toMessageVO(it) }
        return ConversationVO(conversation.id,
            conversationServiceImpl.nameSecondParty(conversation, userId),
            conversationMessages)
    }

    fun toConversationListVO(conversations: ArrayList<Conversation>, userId: Long): ConversationListVO {
        val conversationVOList = conversations.map { toConversationVO(it,userId) }
        return ConversationListVO(conversationVOList)
    }
}