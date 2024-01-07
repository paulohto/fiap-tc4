package com.tc4.streaming.adapters.gateways;

import com.tc4.streaming.adapters.interfaces.IDatabaseClient;
import com.tc4.streaming.adapters.interfaces.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class VideoGateway implements IVideoGateway {

    private final IDatabaseClient databaseClient;

    public VideoGateway(IDatabaseClient databaseClient){
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<VideoEntity> GravarVideo(VideoEntity video) {
        // Implemente a lógica necessária e retorne um Mono contendo a entidade salva
        return Mono.just(video); // ajuste conforme a lógica de gravação real
    }
//    public VideoEntity GravarVideo(VideoEntity video) {
//        return this.databaseClient.GravarVideo( video );
//    }


    @Override
    public Mono<VideoEntity> ObterVideoPorCategoria(String obterPorCategoria) {
        // Implemente a lógica necessária e retorne um Mono contendo a entidade desejada
        return Mono.just(new VideoEntity("Título", "Descrição", "URL", LocalDateTime.now(), "Categoria"));
    }
//    public VideoEntity ObterVideoPorCategoria(String obterPorCategoria) {
//        return this.databaseClient.ObterVideoPorCategoria( obterPorCategoria );
//    }



}
