package com.example.socket.Websocket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Color> colors;

    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
        // Load colors from file
        try {
            File file = new File("color.json");
            if (file.exists()) {
                colors = objectMapper.readValue(file, new TypeReference<List<Color>>() {});
            } else {
                colors = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            colors = new ArrayList<>(); // Initialize colors to an empty list if an error occurs
        }
    }
    

    @MessageMapping("/setcolor")
    public void setColor(@Payload Color color) throws IOException {
        Optional<Color> existingColor = colors.stream().filter(c -> c.getId() == color.getId()).findFirst();
        if (existingColor.isPresent()) {
            // Update existing color
            existingColor.get().setDisponivel(color.isDisponivel());
        } else {
            // Add new color
            colors.add(color);
        }
        // Save colors to file
        objectMapper.writeValue(new File("color.json"), colors);

        template.convertAndSend("/topic/newColor", color);
    }

    @GetMapping("/color/{id}")
    public Color getColor(@PathVariable int id) throws IOException {
        Optional<Color> existingColor = colors.stream().filter(c -> c.getId() == id).findFirst();
        if (existingColor.isPresent()) {
            return existingColor.get();
        } else {
            Color newColor = new Color(id, true);
            colors.add(newColor);
            objectMapper.writeValue(new File("color.json"), colors);
            return newColor;
        }
    }
}

