package com.tc4.streaming.infrastructure.controllers;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import com.tc4.streaming.usercases.VideoCrudUseCase;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

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

    //LISTAGEM GERAL
    @GetMapping
    Flux<VideoEntityAux> obterTodosVideos(){
        //VideoEntity videoEntityBusinessObj = videoDTOMapper.toVideoEntity(request);
        return this.videoCrudUseCase.obterTodosVideos();
    }

    //PAGINAÇÃO
    @GetMapping("/pagina-videos")
    public ResponseEntity<Flux<VideoEntityAux>> obterVideosPaginaveis(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Flux<VideoEntityAux> videos = videoCrudUseCase.obterVideosPaginaveis(page,size);
        return ResponseEntity.ok(videos);
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

    @GetMapping("/categoria")
    Flux<VideoEntityAux> obterVideoPorCategoria(@RequestParam("categoria") String categoria){
        return this.videoCrudUseCase.obterVideoPorCategoria(categoria);
    }

    @GetMapping("/titulo")
    Flux<VideoEntityAux> obterVideoPorTitulo(@RequestParam("titulo") String titulo){
        return this.videoCrudUseCase.obterVideoPorTitulo(titulo);
    }

    @GetMapping("/data")
    Flux<VideoEntityAux> obterVideoPorData(@RequestParam("data") LocalDate data){
        return this.videoCrudUseCase.obterVideoPorData(data);
    }

    @GetMapping("/titulo-data")
    Flux<VideoEntityAux> obterVideoPorTituloEData(@RequestParam("titulo") String titulo, @RequestParam("data") LocalDate data){
        return this.videoCrudUseCase.obterVideoPorTituloEData(titulo,data);
    }

}
