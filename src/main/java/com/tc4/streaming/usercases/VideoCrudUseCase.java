package com.tc4.streaming.usercases;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VideoCrudUseCase {


    private IVideoGateway ivideoGateway;

    public VideoCrudUseCase(IVideoGateway ivideoGateway){
        this.ivideoGateway = ivideoGateway;
    }

    public Mono<VideoEntity> criarVideo(VideoEntity video){
        return ivideoGateway.criarVideo(video);
    }

    //LISTAGEM GERAL
    public Flux<VideoEntityAux> obterTodosVideos(){
        return ivideoGateway.obterTodosVideos();
    }

    //PAGINACAO
    public Flux<VideoEntityAux> obterVideosPaginaveis(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ivideoGateway.obterVideosPaginaveis(pageable);
    }

    public Mono<VideoEntityAux> obterVideoPorCodigo(String id){
        return ivideoGateway.obterVideoPorCodigo(id);
    }

    public Mono<VideoEntity> editarVideo(String videoId, VideoEntityAux videoEditado){
        return ivideoGateway.editarVideo(videoId,videoEditado);
    }

    public Mono<Void> apagarVideo(String videoId) {
        return ivideoGateway.apagarVideo(videoId);
    }

    public Flux<VideoEntityAux> obterVideoPorCategoria(String categoria){
        return ivideoGateway.obterVideoPorCategoria(categoria);
    }

    public Flux<VideoEntityAux> obterVideoPorTitulo(String titulo){
        return ivideoGateway.obterVideoPorTitulo(titulo);
    }

    public Flux<VideoEntityAux> obterVideoPorData(LocalDate data){
        return ivideoGateway.obterVideoPorData(data);
    }

    public Flux<VideoEntityAux> obterVideoPorTituloEData(String titulo, LocalDate data){
        return ivideoGateway.obterVideoPorTituloEData(titulo, data);
    }



}
