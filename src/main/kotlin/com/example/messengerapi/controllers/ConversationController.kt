package com.example.messengerapi.controllers

import com.example.messengerapi.components.ConversationAssembler
import com.example.messengerapi.helpers.objects.ConversationListVO
import com.example.messengerapi.helpers.objects.ConversationVO
import com.example.messengerapi.models.Conversation
import com.example.messengerapi.models.User
import com.example.messengerapi.repositaries.ConversationRepository
import com.example.messengerapi.repositaries.UserRepository
import com.example.messengerapi.services.ConversationServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping
class ConversationController(val conversationService: ConversationServiceImpl,
                             val userRepository: UserRepository,
                             val conversationAssembler: ConversationAssembler) {

    @GetMapping
    fun list(request: HttpServletRequest): ResponseEntity<ConversationListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversations = conversationService.listUserConversation(user.id)

        return  ResponseEntity.ok(conversationAssembler.toConversationListVO(conversations, user.id))
    }

    @GetMapping
    @RequestMapping("/{conversation_id}")
    fun show(@PathVariable("conversation_id") conversationId: Long, request: HttpServletRequest): ResponseEntity<ConversationVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversationThread = conversationService.retrieveThread(conversationId)

        return ResponseEntity.ok(conversationAssembler.toConversationVO(conversationThread, user.id))
    }
}