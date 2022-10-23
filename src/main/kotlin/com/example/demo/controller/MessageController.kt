package com.example.demo.controller

import com.example.demo.model.Message
import com.example.demo.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
//Constructor injection style in kotlin
class MessageController(val messageService: MessageService) {
    @GetMapping("/all")
    fun getAllMessages(): List<Message> = messageService.findMessages();

    @PostMapping("/create")
    fun createMessage(@RequestBody message: Message) {
        messageService.post(message)
    }
}