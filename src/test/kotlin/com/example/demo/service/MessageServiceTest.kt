package com.example.demo.service

import com.example.demo.model.Message
import com.example.demo.repository.MessageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MessageServiceTest {
    private val messageRepository: MessageRepository = mockk()
    private val messageService: MessageService = MessageService(messageRepository)

    @Test
    fun should_return_message_list_when_fineMessages_is_called(){
        //given
        val messages:List<Message> = listOf(
            Message("1","Hello"),
            Message("2","This is second message")
        )
        every { messageRepository.findMessages() } returns messages

        //when
        val result = messageService.findMessages();

        //then
        verify(exactly = 1) { messageRepository.findMessages() }
        assertEquals(messages, result)

    }

    @Test
    fun should_create_message_when_dbSave_is_called(){
        //given
        val message =  Message("1","Hello")
        every { messageRepository.save(any()) } returns message

        //when
        val result = messageService.post(message)

        //then
        verify(exactly = 1) { messageRepository.save(any()) }
    }
}