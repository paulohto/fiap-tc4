package com.tc4.streaming.adapters.controllers;

import com.tc4.streaming.adapters.gateways.VideoGateway;
import com.tc4.streaming.adapters.interfaces.IDatabaseClient;
import com.tc4.streaming.adapters.interfaces.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.usercases.VideoCrudUseCase;

import java.time.LocalDateTime;

public class VideoController {

    public VideoEntity GravarVideo(
            String titulo,
            String descricao,
            String url,
            LocalDateTime dataDaPublicacao,
            String categoria,
            IDatabaseClient databaseClient
    ){
        IVideoGateway videoGateway = new VideoGateway(databaseClient);

        //var videoEntity = videoGateway.ObterVideoPorCategoria(categoria);

        var video = VideoCrudUseCase.CriarVideo(titulo, descricao, url, dataDaPublicacao, categoria);
        videoGateway.GravarVideo(video);
        return video;
    }
}
