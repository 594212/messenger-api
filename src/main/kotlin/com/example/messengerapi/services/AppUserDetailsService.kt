package com.example.messengerapi.services

import com.example.messengerapi.repositaries.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class AppUserDetailsService( val userRepository: UserRepository ): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val  user = userRepository.findByUsername(username) ?:
        throw UsernameNotFoundException("A user whith the username $username doesn't not exist")

        return User(user.username,user.password, ArrayList<GrantedAuthority>())
    }

}
