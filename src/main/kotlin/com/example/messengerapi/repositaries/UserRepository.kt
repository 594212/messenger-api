package com.example.messengerapi.repositaries

import com.example.messengerapi.models.User
import org.springframework.data.repository.CrudRepository

    interface  UserRepository : CrudRepository<User, Long>{
        fun findByUsername(username: String): User?
        fun findByPhoneNumber(phoneNumber: String): User?
    }