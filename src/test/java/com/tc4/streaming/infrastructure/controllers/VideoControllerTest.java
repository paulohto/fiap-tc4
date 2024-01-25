package com.tc4.streaming.infrastructure.controllers;

import com.mongodb.reactivestreams.client.MongoClient;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.usercases.VideoCrudUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebClient
class VideoControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private VideoCrudUseCase videoCrudUseCase;
    @MockBean
    private VideoDTOMapper videoDTOMapper;
    @MockBean
    private MongoClient mongoClient;

    @Test
    @DisplayName("Cria um video com sucesso")
    void createComSucesso() {

        LocalDateTime dataDaPublicacao = LocalDateTime.parse("2024-01-16T00:30:00");
        CreateVideoRequest request = new CreateVideoRequest( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");
        VideoEntity videoEntity = new VideoEntity( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");


        Mockito.when(videoCrudUseCase.criarVideo(any(VideoEntity.class))).thenReturn(Mono.just(videoEntity));

        webTestClient.post().uri("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated();

    }

    @Test
    void obterTodosVideos() {
    }

    @Test
    void obterVideoPorCodigo() {
    }

    @Test
    void editarVideo() {
    }

    @Test
    void apagarVideo() {
    }
}