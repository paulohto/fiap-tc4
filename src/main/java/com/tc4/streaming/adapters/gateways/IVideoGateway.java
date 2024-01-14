package com.tc4.streaming.adapters.gateways;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVideoGateway {
    //VideoEntity criarVideo(VideoEntity video);
    Mono<VideoEntity> criarVideo(VideoEntity video);
    Flux<VideoEntityAux> obterTodosVideos();
    Mono<VideoEntityAux> obterVideoPorCodigo(String videoId);
    //Mono<VideoEntity> apagarVideo(String videoId);


//    Mono<VideoEntity> GravarVideo(VideoEntity video);
//
//    Mono<VideoEntity> ObterVideoPorCategoria(String categoria);

}
