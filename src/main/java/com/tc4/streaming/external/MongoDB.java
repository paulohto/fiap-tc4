package com.tc4.streaming.external;

import com.tc4.streaming.adapters.interfaces.IDatabaseClient;
import com.tc4.streaming.entities.VideoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public class MongoDB implements IDatabaseClient {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public MongoDB(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<VideoEntity> GravarVideo(VideoEntity video) {
        // Gravar o vídeo no MongoDB reativo e retornar o Mono resultante
        return reactiveMongoTemplate.save(video);
    }

//    public VideoEntity GravarVideo(VideoEntity video) {
//        // Gravar o vídeo no MongoDB reativo
//        return reactiveMongoTemplate.save(video).block();
//    }

//    @Override
//    public VideoEntity GravarVideo(VideoEntity video) {
//        return null;
//    }

    @Override
    public Mono<VideoEntity> ObterVideoPorCategoria(String obterPorCategoria) {
        // Implemente a lógica de obtenção de vídeo por categoria no MongoDB reativo aqui
        return null;
    }
//    @Override
//    public VideoEntity ObterVideoPorCategoria(String obterPorCategoria){
//        return null;
//    }

}
