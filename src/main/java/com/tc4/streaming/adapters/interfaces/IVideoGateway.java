package com.tc4.streaming.adapters.interfaces;

import com.tc4.streaming.entities.VideoEntity;
import reactor.core.publisher.Mono;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IVideoGateway {

    Mono<VideoEntity> GravarVideo(VideoEntity video);

    Mono<VideoEntity> ObterVideoPorCategoria(String categoria);

}
