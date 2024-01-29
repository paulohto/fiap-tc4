package com.tc4.streaming.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "curtidas")
public class CurtidaEntityAux {

    @Id
    private String id;
    private Integer valor; // Pode ser 1 para curtida, -1 para descurtida, etc. conforme necessário

    @DBRef
    private List<VideoEntityAux> videos;

    public CurtidaEntityAux() {
    }

    public CurtidaEntityAux(String id, Integer valor) {
        this.id = id;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

}
