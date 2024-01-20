package com.tc4.streaming.infrastructure.controllers;

import java.time.LocalDateTime;

public record CreateVideoRequest (
        String id,
        String titulo,
        String descricao,
        String url,
        LocalDateTime dataDaPublicacao,
        String categoria
){

}
