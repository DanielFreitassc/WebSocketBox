package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class StatusRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    private final File file = new File("statuses.json");
    
    
    public StatusRepository() {
        // Cria o arquivo se ele não existir
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Status> getStatuses() {
        List<Status> statuses = new ArrayList<>();
        try {
            // Lê os status do arquivo JSON
            statuses = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Status.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    public void saveStatuses(List<Status> statuses) {
        try {
            // Escreve os status no arquivo JSON
            objectMapper.writeValue(file, statuses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
