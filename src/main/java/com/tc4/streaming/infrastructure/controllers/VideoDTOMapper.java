package com.tc4.streaming.infrastructure.controllers;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.usercases.VideoCrudUseCase;

import java.time.LocalDateTime;

public class VideoDTOMapper {
    CreateVideoResponse toResponse(VideoEntity videoEntity){
        return new CreateVideoResponse(
                videoEntity.getTitulo(),
                videoEntity.getDescricao(),
                videoEntity.getUrl(),
                videoEntity.getDataDaPublicacao(),
                videoEntity.getCategoria()
        );
    }

    public VideoEntity toVideoEntity(CreateVideoRequest request){
        return  new VideoEntity(
                request.titulo(),
                request.descricao(),
                request.url(),
                request.dataDaPublicacao(),
                request.categoria()
        );
    }
}
