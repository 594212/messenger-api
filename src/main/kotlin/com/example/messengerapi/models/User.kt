package com.example.messengerapi.models

import com.example.messengerapi.listeners.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size


@Entity
@Table(name ="`user`")
@EntityListeners(UserListener::class)
class User (
    @Column(unique = true)
    @Size(min = 2)
    var username: String = "",
    @Size(min = 11)
    @Pattern(regexp="^[+]?[7,8]?[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*\$")
    var phoneNumber: String = "",
    @Size(min =60, max = 60)
    var password: String = "",
    var status: String = "available",
    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    var accoutStatus: String = "activated",
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @DateTimeFormat
    val createdAt: Date = Date.from(Instant.now())
) {
    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    private var sentMessage: Collection<Message>? = null
    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var receivedMessages: Collection<Message>? = null
}