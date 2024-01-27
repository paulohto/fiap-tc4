package com.tc4.streaming.usercases;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VideoCrudUseCaseTest {

    @Mock
    private IVideoGateway ivideoGateway;

    @InjectMocks
    private VideoCrudUseCase videoCrudUseCase;

    @Test
    void criarVideo() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntity videoEntity = new VideoEntity( "","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(ivideoGateway.criarVideo(any(VideoEntity.class))).thenReturn(Mono.just(videoEntity));

        Mono<VideoEntity> video = videoCrudUseCase.criarVideo(videoEntity);

        StepVerifier.create(video)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(ivideoGateway, times(1)).criarVideo(any(VideoEntity.class));
    }

    @Test
    void obterTodosVideos() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(ivideoGateway.obterTodosVideos()).thenReturn(Flux.just(videoEntityAux));

        Flux<VideoEntityAux> video = videoCrudUseCase.obterTodosVideos();

        StepVerifier.create(video)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(ivideoGateway, times(1)).obterTodosVideos();

    }

    @Test
    void obterVideoPorCodigo() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(ivideoGateway.obterVideoPorCodigo(anyString())).thenReturn(Mono.just(videoEntityAux));

        Mono<VideoEntityAux> video = videoCrudUseCase.obterVideoPorCodigo("1234");

        StepVerifier.create(video)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(ivideoGateway, times(1)).obterVideoPorCodigo(anyString());
    }

    @Test
    void apagarVideo() {

        Mockito.when(ivideoGateway.apagarVideo("1234")).thenReturn(Mono.empty());

        Mono<Void> video = videoCrudUseCase.apagarVideo("1234");

        StepVerifier.create(video)
                .expectComplete()
                .verify();

        Mockito.verify(ivideoGateway, times(1)).apagarVideo(anyString());
    }

    @Test
    void editarVideo() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");
        VideoEntity videoEntity = new VideoEntity( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(ivideoGateway.editarVideo("1234", videoEntityAux)).thenReturn(Mono.just(videoEntity));

        Mono<VideoEntity> video = videoCrudUseCase.editarVideo("1234", videoEntityAux);

        StepVerifier.create(video)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(ivideoGateway, times(1)).editarVideo(anyString(),any(VideoEntityAux.class));
    }

    @Test
    void obterVideoPorCategoria() {
    }

    @Test
    void obterVideoPorTitulo() {
    }

    @Test
    void obterVideoPorData() {
    }

    @Test
    void obterVideoPorTituloEData() {
    }
}