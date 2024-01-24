package com.tc4.streaming.infrastructure.controllers;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import com.tc4.streaming.usercases.VideoCrudUseCase;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private VideoCrudUseCase videoCrudUseCase;
    private VideoDTOMapper videoDTOMapper;

    public VideoController(VideoCrudUseCase videoCrudUseCase, VideoDTOMapper videoDTOMapper) {
        this.videoCrudUseCase = videoCrudUseCase;
        this.videoDTOMapper = videoDTOMapper;
    }

    @PostMapping
    Mono<CreateVideoResponse> create(@RequestBody CreateVideoRequest request){
        VideoEntity videoEntityBusinessObj = videoDTOMapper.toVideoEntity(request);
        return videoCrudUseCase.criarVideo(videoEntityBusinessObj)
                .map(videoDTOMapper::toResponse);
    }

    @GetMapping
    Flux<VideoEntityAux> obterTodosVideos(){
        //VideoEntity videoEntityBusinessObj = videoDTOMapper.toVideoEntity(request);
        return this.videoCrudUseCase.obterTodosVideos();
    }
    @GetMapping("/{id}")
    Mono<VideoEntityAux> obterVideoPorCodigo(@PathVariable String id){
        return this.videoCrudUseCase.obterVideoPorCodigo(id);
    }

    @PutMapping("/editar/{id}")
    Mono<VideoEntity> editarVideo(@PathVariable String id, @RequestBody VideoEntityAux videoEditado){
        return this.videoCrudUseCase.editarVideo(id,videoEditado);
    }

    @DeleteMapping("/apagar/{id}")
    Mono<Void> apagarVideo(@PathVariable String id){
        return this.videoCrudUseCase.apagarVideo(id);
    }

}
