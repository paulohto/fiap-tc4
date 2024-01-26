package com.tc4.streaming.adapters.gateways;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IVideoGateway {
    Mono<VideoEntity> criarVideo(VideoEntity video);

    //LISTAGEM GERAL
    Flux<VideoEntityAux> obterTodosVideos();

    // PAGINACAO
    Flux<VideoEntityAux> obterVideosPaginaveis(Pageable pageable);

    Mono<VideoEntityAux> obterVideoPorCodigo(String videoId);
    Mono<VideoEntity> editarVideo(String videoId, VideoEntityAux videoEditado);
    Mono<Void> apagarVideo(String videoId);
    Flux<VideoEntityAux> obterVideoPorCategoria(String categoria);
    Flux<VideoEntityAux> obterVideoPorTitulo(String titulo);
    Flux<VideoEntityAux> obterVideoPorData(LocalDate data);
    Flux<VideoEntityAux> obterVideoPorTituloEData(String titulo, LocalDate data);
}
