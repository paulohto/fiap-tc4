package com.tc4.streaming.entities;

import org.springframework.data.annotation.Id;

public class CurtidaEntity {

    private final String id;
    private final Integer valor; // Pode ser 1 para curtida, -1 para descurtida, etc. conforme necessário

    public CurtidaEntity(String id, Integer valor) {
        this.id = id;
        this.valor = valor;
    }

    public String getId() { return id; }
    public Integer getValor() { return valor; }

}

