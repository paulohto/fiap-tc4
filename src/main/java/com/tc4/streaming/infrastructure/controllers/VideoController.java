package com.tc4.streaming.infrastructure.controllers;

import com.tc4.streaming.entities.CurtidaEntity;
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

    // LISTAGEM GERAL
    @GetMapping
    Flux<VideoEntityAux> obterTodosVideos(){
        return this.videoCrudUseCase.obterTodosVideos();
    }

    // PAGINAÇÃO
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

    // BLOCO CONSULTA VIDEOS POR...
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

    // BLOCO CURTIDAS
    @PostMapping("/curtir/{id}")
    public Mono<VideoEntityAux> adicionarCurtida(@PathVariable String id, @RequestParam("curtir") Integer valor) {
        // Criar uma instância de CurtidaEntity
        CurtidaEntity curtida = new CurtidaEntity(id, valor); // ou qualquer outro valor desejado
        // Chamar o método de adição de curtida no seu caso de uso
        return videoCrudUseCase.adicionarCurtida(id, curtida)
                .then(Mono.defer(() -> videoCrudUseCase.obterVideoPorCodigo(id)))
                .switchIfEmpty(Mono.error(new RuntimeException("Vídeo não encontrado"))); // Trate o caso em que o vídeo não é encontrado
    }

    @GetMapping("/curtidas-lista-desc")
    Flux<VideoEntityAux> obterVideosCurtidasDescendentes() {
        return videoCrudUseCase.obterVideosCurtidasDescendente();
    }

    @GetMapping("/top-recomendados")
    Flux<VideoEntityAux> obterVideosTop(@RequestParam("limit") Integer limit) {
        return videoCrudUseCase.obterVideosTop(limit);
    }


}
