package com.tc4.streaming.adapters.gateways;

import com.tc4.streaming.entities.CurtidaEntity;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IVideoGateway {

    // CRIA VIDEO
    Mono<VideoEntity> criarVideo(VideoEntity video);

    // LISTAGEM GERAL
    Flux<VideoEntityAux> obterTodosVideos();

    // PAGINACAO
    Flux<VideoEntityAux> obterVideosPaginaveis(Pageable pageable);

    // CONSULTA LISTA DE VIDEOS
    Mono<VideoEntityAux> obterVideoPorCodigo(String videoId);

    // EDITA VIDEOS POR ID
    Mono<VideoEntity> editarVideo(String videoId, VideoEntityAux videoEditado);

    // APAGA VIDEOS POR ID
    Mono<Void> apagarVideo(String videoId);

    // BLOCO CONSULTA VIDEOS POR...
    Flux<VideoEntityAux> obterVideoPorCategoria(String categoria);
    Flux<VideoEntityAux> obterVideoPorTitulo(String titulo);
    Flux<VideoEntityAux> obterVideoPorData(LocalDate data);
    Flux<VideoEntityAux> obterVideoPorTituloEData(String titulo, LocalDate data);

    // ADICIONA CURTIDA AO VIDEO
    Mono<Void> adicionarCurtida(String videoId, CurtidaEntity curtida);

    // LISTAGEM CURTIDOS DESC
    Flux<VideoEntityAux> obterVideosCurtidasDescendente();

    // LISTAGEM MAIS CURTIDOS RECOMENDADOS
    Flux<VideoEntityAux> obterVideosTop(Integer limit);
}
