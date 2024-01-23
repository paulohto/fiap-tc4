package com.tc4.streaming.usercases;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VideoCrudUseCaseTest {

    @Mock
    private IVideoGateway ivideoGateway;

    @InjectMocks
    private VideoCrudUseCase videoCrudUseCase;

    @Test
    void criarVideo() {

        LocalDateTime dataDaPublicacao = LocalDateTime.parse("2024-01-16T00:30:00");
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

    }

    @Test
    void obterVideoPorCodigo() {
    }

    @Test
    void apagarVideo() {
    }
}