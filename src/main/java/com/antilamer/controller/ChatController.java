package com.antilamer.controller;

import com.antilamer.dto.chat.MessageDTO;
import com.antilamer.dto.chat.OutputMessageDTO;
import com.antilamer.service.UserBOImpl;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {

    private static Logger logger = Logger.getLogger(ChatController.class);

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public OutputMessageDTO sendMessage(SimpMessageHeaderAccessor headerAccessor, MessageDTO message) {
        return new OutputMessageDTO(message, new Date(), headerAccessor.getUser());
    }
}