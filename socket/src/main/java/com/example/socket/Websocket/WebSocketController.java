package com.example.socket.Websocket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/setcolor")
    public void setColor(@Payload Color color) throws IOException {
        objectMapper.writeValue(new File("color.json"), color);

        template.convertAndSend("/topic/newColor", color);
    }


    @GetMapping("/color")
    public Color getColor() throws IOException {
        // Lê o estado salvo do arquivo JSON
        Color color = objectMapper.readValue(Files.readAllBytes(Paths.get("color.json")), Color.class);

        return color;
    }
}

