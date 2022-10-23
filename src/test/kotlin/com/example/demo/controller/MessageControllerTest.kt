package com.example.demo.controller

import com.example.demo.model.Message
import com.example.demo.service.MessageService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.justRun
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class MessageControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    // lateinit as the name suggests this variable will be initialized in the future.
    lateinit var messageService: MessageService

    @Test
    fun should_return_list_of_messages_when_queries() {
        val messages: List<Message> = listOf(
            Message("1", "Hello"),
            Message("2", "This is second message")
        )
        every { messageService.findMessages() } returns messages

        mockMvc.perform(get("/all"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("[{\"id\":\"1\",\"text\":\"Hello\"},{\"id\":\"2\",\"text\":\"This is second message\"}]"))
    }

    @Test
    fun should_save_record_when_post_is_called() {
        val message = "{\"id\":\"1\",\"text\":\"a post message\"}"
        // for method with void return type
        justRun { messageService.post(any()) }

        mockMvc.perform(
            post("/create")
                .content(message)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }
}
