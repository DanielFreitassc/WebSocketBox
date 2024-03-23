package com.example.demo;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class StatusController {

    private final SimpMessagingTemplate messagingTemplate;
    private final StatusRepository statusRepository;

    public StatusController(SimpMessagingTemplate messagingTemplate, StatusRepository statusRepository) {
        this.messagingTemplate = messagingTemplate;
        this.statusRepository = statusRepository;
    }

    @MessageMapping("/status")
    public void handleStatusUpdate(Status status) {
        // Verifica se o status com o mesmo id já existe
        List<Status> statuses = statusRepository.getStatuses();
        boolean found = false;
        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i).getId().equals(status.getId())) {
                // Atualiza o status existente
                statuses.set(i, status);
                found = true;
                break;
            }
        }

        // Se não foi encontrado, adiciona o novo status
        if (!found) {
            statuses.add(status);
        }

        // Envie o status atualizado para os clientes
        messagingTemplate.convertAndSend("/topic/status", status);

        // Salva os status atualizados no repositório
        statusRepository.saveStatuses(statuses);
    }
}
