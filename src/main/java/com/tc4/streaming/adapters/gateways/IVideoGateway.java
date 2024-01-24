package com.tc4.streaming.adapters.gateways;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVideoGateway {
    Mono<VideoEntity> criarVideo(VideoEntity video);
    Flux<VideoEntityAux> obterTodosVideos();
    Mono<VideoEntityAux> obterVideoPorCodigo(String videoId);
    Mono<Void> apagarVideo(String videoId);

}
