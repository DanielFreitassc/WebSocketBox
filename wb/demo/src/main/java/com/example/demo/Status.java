package com.example.demo;

public class Status {

    private Long id;
    private boolean disponivel;

    public Status() {
    }

    public Status(Long id, boolean disponivel) {
        this.id = id;
        this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}
