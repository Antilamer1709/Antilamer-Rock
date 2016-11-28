package com.antilamer.controller;

import com.antilamer.beans.chat.MessageBean;
import com.antilamer.beans.chat.OutputMessageBean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public OutputMessageBean sendMessage(MessageBean message) {
        return new OutputMessageBean(message, new Date());
    }
}