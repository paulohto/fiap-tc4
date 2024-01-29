package com.tc4.streaming.infrastructure.controllers;

import com.tc4.streaming.infrastructure.persistence.CurtidaEntityAux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CreateVideoResponse(
        String id,
        String titulo,
        String descricao,
        String url,
        LocalDate dataDaPublicacao,
        String categoria
) {

}
