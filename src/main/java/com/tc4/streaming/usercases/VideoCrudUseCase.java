package com.tc4.streaming.usercases;

import com.tc4.streaming.entities.VideoEntity;
import java.time.LocalDateTime;

public class VideoCrudUseCase {

    public static VideoEntity CriarVideo(
            String titulo,
            String descricao,
            String url,
            LocalDateTime dataDaPublicacao,
            String categoria
    ){
        VideoEntity novoVideo = new VideoEntity(titulo, descricao, url, dataDaPublicacao, categoria);
        return novoVideo;
    }
}
