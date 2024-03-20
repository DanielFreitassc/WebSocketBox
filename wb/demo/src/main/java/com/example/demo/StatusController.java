package com.example.demo;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final SimpMessagingTemplate messagingTemplate;
    private final StatusRepository statusRepository;

    public StatusController(SimpMessagingTemplate messagingTemplate, StatusRepository statusRepository) {
        this.messagingTemplate = messagingTemplate;
        this.statusRepository = statusRepository;
    }

    @PutMapping("/{id}")
    public void handleStatusUpdate(@PathVariable Long id, @RequestBody Status status) {
        // Verifica se o status com o mesmo id já existe
        List<Status> statuses = statusRepository.getStatuses();
        boolean found = false;
        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i).getId().equals(id)) {
                // Atualiza o status existente com os dados fornecidos na solicitação PUT
                statuses.get(i).setDisponivel(status.isDisponivel());
                found = true;
                break;
            }
        }


        // Envie o status atualizado para os clientes
        messagingTemplate.convertAndSend("/topic/status", status);

        // Salva os status atualizados no repositório
        statusRepository.saveStatuses(statuses);
    }
}
