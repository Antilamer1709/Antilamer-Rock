package com.antilamer.controller;

import com.antilamer.dto.chat.MessageDTO;
import com.antilamer.dto.chat.OutputMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public OutputMessageDTO sendMessage(MessageDTO message) {
        return new OutputMessageDTO(message, new Date());
    }
}