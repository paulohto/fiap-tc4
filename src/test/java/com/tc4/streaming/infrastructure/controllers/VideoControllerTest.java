package com.tc4.streaming.infrastructure.controllers;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.CurtidaEntity;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import com.tc4.streaming.usercases.VideoCrudUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class VideoControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private VideoCrudUseCase videoCrudUseCase;
    @MockBean
    private VideoDTOMapper videoDTOMapper;

    @MockBean
    private IVideoGateway ivideoGateway;

    @InjectMocks
    private VideoController videoController;

    @Test
    @DisplayName("Cria um video com sucesso")
    void createComSucesso() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        CreateVideoRequest request = new CreateVideoRequest( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");
        VideoEntity videoEntity = new VideoEntity( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");
        CreateVideoResponse response = new CreateVideoResponse( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoDTOMapper.toVideoEntity(any(CreateVideoRequest.class))).thenReturn(videoEntity);
        Mockito.when(videoCrudUseCase.criarVideo(any(VideoEntity.class))).thenReturn(Mono.just(videoEntity));
        Mockito.when(videoDTOMapper.toResponse(any(VideoEntity.class))).thenReturn(response);

        webTestClient.post().uri("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(videoCrudUseCase).criarVideo(any(VideoEntity.class));
    }

    @Test
    @DisplayName("Não cria um video com sucesso")
    void createSemSucesso() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        CreateVideoRequest request = new CreateVideoRequest( "","", "", "", dataDaPublicacao, "");

        webTestClient.post().uri("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    @DisplayName("Obter todos os videos com sucesso")
    void obterTodosVideosComSucesso() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterTodosVideos()).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri("/videos")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]id").isEqualTo("1234");
    }

    @Test
    @DisplayName("Obter um video por codigo com sucesso")
    void obterVideoPorCodigoComSucesso() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideoPorCodigo(anyString())).thenReturn(Mono.just(videoEntityAux));

        webTestClient.get().uri("/videos/1234")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                        .expectBody()
                                .jsonPath("$.id").isEqualTo("1234");

        Mockito.verify(videoCrudUseCase).obterVideoPorCodigo(anyString());

    }

    @Test
    @DisplayName("Obter um video por codigo sem sucesso")
    void obterVideoPorCodigoSemSucesso() {

        webTestClient.get().uri("/videos/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    @DisplayName("Edita um video por codigo com sucesso")
    void editarVideoComSecesso() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");
        VideoEntity videoEntity = new VideoEntity( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.editarVideo("1234", videoEntityAux)).thenReturn(Mono.just(videoEntity));

        webTestClient.put().uri("/videos/editar/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(videoEntityAux))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("Apaga um video por codigo com sucesso")
    void apagarVideoComSucesso() {


        Mockito.when(videoCrudUseCase.apagarVideo("1234")).thenReturn(Mono.empty());

        webTestClient.delete().uri("/videos/apagar/1234")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(videoCrudUseCase).apagarVideo(anyString());

    }
    @Test
    @DisplayName("Apaga um video por codigo sem sucesso")
    void apagarVideoSemSucesso() {

        webTestClient.delete().uri("/videos/ ")
                .exchange()
                .expectStatus().is4xxClientError();

    }

    @Test
    @DisplayName("Obtem videos por categoria com sucesso")
    void obterVideoPorCategoriaComSucesso() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideoPorCategoria(anyString())).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/videos/categoria")
                        .queryParam("categoria", "filme")
                        .build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("Obtem videos por titulo com sucesso")
    void obterVideoPorTitulo() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideoPorTitulo(anyString())).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/videos/titulo")
                        .queryParam("titulo", "Tema")
                        .build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void obterVideoPorData() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideoPorData(any())).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/videos/data")
                        .queryParam("data", "2024-01-16")
                        .build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void obterVideoPorTituloEData() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideoPorTituloEData(anyString(), any())).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/videos/titulo-data")
                        .queryParam("titulo", "Tema")
                        .queryParam("data", "2024-01-16")
                        .build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void obterVideosPaginaveis() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideosPaginaveis(0, 10)).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/videos/pagina-videos")
                        .queryParam("page", 0)
                        .queryParam("size", 10)
                        .build())
                .exchange()
                .expectStatus().isOk();
    }

//    @Test
//    @DisplayName("Adiciona curtidas com sucesso")
//    void adicionarCurtida() {
//
//        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
//        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");
//        CurtidaEntity curtida = new CurtidaEntity("1234", 1);
//
//        Mockito.when(ivideoGateway.adicionarCurtida("1234", curtida)).thenReturn(Mono.empty());
//        Mockito.when(videoCrudUseCase.obterVideoPorCodigo("1234")).thenReturn(Mono.just(videoEntityAux));
//        Mockito.when(videoCrudUseCase.adicionarCurtida("1234", curtida)).thenReturn(Mono.empty());
//
//        webTestClient.post().uri(uriBuilder -> uriBuilder
//                        .path("/videos/curtir/1234")
//                        .queryParam("curtir", 1)
//                        .build())
//                .exchange()
//                .expectStatus().isOk();
//
//        Mockito.verify(videoCrudUseCase).adicionarCurtida(anyString(), any(CurtidaEntity.class));
//    }

    @Test
    @DisplayName("Obtem videos curtidas descendentes com sucesso")
    void obterVideosCurtidasDescendentes() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideosCurtidasDescendente()).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri("/videos/curtidas-lista-desc")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("Obtem videos top com sucesso")
    void obterVideosTop() {

        LocalDate dataDaPublicacao = LocalDate.parse("2024-01-16");
        VideoEntityAux videoEntityAux = new VideoEntityAux( "1234","Tema", "Filme", "https://filme.com", dataDaPublicacao, "filme");

        Mockito.when(videoCrudUseCase.obterVideosTop(any())).thenReturn(Flux.just(videoEntityAux));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path("/videos/top-recomendados")
                        .queryParam("limit", 1)
                        .build())
                .exchange()
                .expectStatus().isOk();
    }
}