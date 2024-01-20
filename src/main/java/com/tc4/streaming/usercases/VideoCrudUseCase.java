package com.tc4.streaming.usercases;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VideoCrudUseCase {

//    public static VideoEntity CriarVideo(
//            String titulo,
//            String descricao,
//            String url,
//            LocalDateTime dataDaPublicacao,
//            String categoria
//    ){
//        VideoEntity novoVideo = new VideoEntity(titulo, descricao, url, dataDaPublicacao, categoria);
//        return novoVideo;
//    }

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


}
