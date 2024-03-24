package com.example.socket.Websocket;


import lombok.Data;
@Data
public class Color {
    private int id;
    private boolean disponivel;


    public Color(int id, boolean disponivel) {
        this.id = id;
        this.disponivel = disponivel;
    }
}
