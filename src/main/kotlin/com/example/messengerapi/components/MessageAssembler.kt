package com.example.messengerapi.components

import com.example.messengerapi.helpers.objects.MessageVO
import com.example.messengerapi.models.Message
import org.springframework.stereotype.Component

@Component
class MessageAssembler {

    fun toMessageVO( message: Message): MessageVO {
        return MessageVO(message.id, message.sender?.id, message.recipient?.id,
            message.conversation?.id, message.body,message.createdAt.toString())
    }


}