package com.tc4.streaming.adapters.interfaces;

import com.tc4.streaming.entities.VideoEntity;
import reactor.core.publisher.Mono;

public interface IDatabaseClient extends IVideoGateway {
    //VideoEntity CriarVideoNoBD(String criarVideo);
    Mono<VideoEntity> GravarVideo(VideoEntity video);

    Mono<VideoEntity> ObterVideoPorCategoria(String categoria);

}
