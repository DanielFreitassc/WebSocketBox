package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StatusController {

    private final SimpMessagingTemplate messagingTemplate;

    public StatusController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/status")
    public void handleStatusUpdate(Status status) {
        messagingTemplate.convertAndSend("/topic/status", status);
    }

}

