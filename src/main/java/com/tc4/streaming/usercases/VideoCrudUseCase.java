package com.tc4.streaming.usercases;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VideoCrudUseCase {


    private IVideoGateway ivideoGateway;

    public VideoCrudUseCase(IVideoGateway ivideoGateway){
        this.ivideoGateway = ivideoGateway;
    }

    public Mono<VideoEntity> criarVideo(VideoEntity video){
        return ivideoGateway.criarVideo(video);
    }

    public Flux<VideoEntityAux> obterTodosVideos(){
        return ivideoGateway.obterTodosVideos();
    }

    public Mono<VideoEntityAux> obterVideoPorCodigo(String id){
        return ivideoGateway.obterVideoPorCodigo(id);
    }

    public Mono<Void> apagarVideo(String videoId) {
        return ivideoGateway.apagarVideo(videoId);
    }

    public Mono<VideoEntity> editarVideo(String videoId, VideoEntityAux videoEditado){
        return ivideoGateway.editarVideo(videoId,videoEditado);
    }
}
