package com.tc4.streaming.infrastructure.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateVideoResponse(
        String id,
        String titulo,
        String descricao,
        String url,
        LocalDate dataDaPublicacao,
        String categoria
        //Integer gostei
) {

}
