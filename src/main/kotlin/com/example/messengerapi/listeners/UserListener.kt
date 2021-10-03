package com.example.messengerapi.listeners

import com.example.messengerapi.models.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class UserListener {

    @PrePersist
    @PreUpdate
    fun hashPassword(user: User){
        user.password = BCryptPasswordEncoder().encode(user.password)
    }

}