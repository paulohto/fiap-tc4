package com.tc4.streaming.adapters.gateways;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.usercases.VideoCrudUseCase;
import reactor.core.publisher.Mono;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IVideoGateway {
    //VideoEntity criarVideo(VideoEntity video);
    Mono<VideoEntity> criarVideo(VideoEntity video);

//    Mono<VideoEntity> GravarVideo(VideoEntity video);
//
//    Mono<VideoEntity> ObterVideoPorCategoria(String categoria);

}
