package com.tc4.streaming.infrastructure.gateway;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.CurtidaEntity;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.CurtidaEntityAux;
import com.tc4.streaming.infrastructure.persistence.ICurtidaRepository;
import com.tc4.streaming.infrastructure.persistence.IVideoRepository;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VideoRepositoryGateway implements IVideoGateway {

    private final MongoTemplate mongoTemplate;
    private final IVideoRepository ivideoRepository;
    private final VideoEntityAuxMapper videoEntityAuxMapper;
    // Mono<Void> adicionarCurtida(String videoId, Curtida curtida);
    //private final ICurtidaRepository icurtidaRepository;


    public VideoRepositoryGateway(MongoTemplate mongoTemplate, IVideoRepository ivideoRepository, VideoEntityAuxMapper videoEntityAuxMapper) {
        this.mongoTemplate = mongoTemplate;
        this.ivideoRepository = ivideoRepository;
        this.videoEntityAuxMapper = videoEntityAuxMapper;
    }

    @Override
    public Mono<VideoEntity> criarVideo(VideoEntity videoDomainObj) {
        VideoEntityAux videoEntityAux = videoEntityAuxMapper.toEntity(videoDomainObj);
        return ivideoRepository.save(videoEntityAux)
                .map(videoEntityAuxMapper::toDomainObj);
    }

    // LISTAGEM GERAL
    @Override
    public Flux<VideoEntityAux> obterTodosVideos(){
        return this.ivideoRepository.findAll();
    }

    // PAGINAÇÃO
    @Override
    public Flux<VideoEntityAux> obterVideosPaginaveis(Pageable pageable) {
        Query query = new Query().with(Sort.by(Sort.Order.desc("dataDaPublicacao"))).with(pageable);
        return Flux.fromIterable(mongoTemplate.find(query, VideoEntityAux.class));
    }

    // CONSULTA LISTA DE VIDEOS
    @Override
    public Mono<VideoEntityAux> obterVideoPorCodigo(String videoId) {
        return this.ivideoRepository
                .findById(videoId);
    }

    // EDITA VIDEOS POR ID
    @Override
    public Mono<VideoEntity> editarVideo(String videoId, VideoEntityAux videoEditado) {
        return this.ivideoRepository.findById(videoId)
                .flatMap(existingVideo -> {
                    VideoEntityAux updatedVideoAux = new VideoEntityAux(
                            existingVideo.getId(),
                            videoEditado.getTitulo(),
                            videoEditado.getDescricao(),
                            videoEditado.getUrl(),
                            videoEditado.getDataDaPublicacao(),
                            videoEditado.getCategoria()

                    );
                    return ivideoRepository.save(updatedVideoAux)
                            .map(videoEntityAuxMapper::toDomainObj);
                });
    }

    // APAGA VIDEOS POR ID
    @Override
    public Mono<Void> apagarVideo(String videoId) {
        return ivideoRepository.deleteById(videoId);
    }

    // BLOCO CONSULTA VIDEOS POR...
    @Override
    public Flux<VideoEntityAux> obterVideoPorCategoria(String categoria) {
        Query query = new Query(Criteria.where("categoria").is(categoria));
        return Flux.fromIterable(mongoTemplate.find(query, VideoEntityAux.class));
    }

    @Override
    public Flux<VideoEntityAux> obterVideoPorTitulo(String titulo) {
        Query query = new Query(Criteria.where("titulo").is(titulo));
        return Flux.fromIterable(mongoTemplate.find(query, VideoEntityAux.class));
    }

    @Override
    public Flux<VideoEntityAux> obterVideoPorData(LocalDate data) {
        Query query = new Query(Criteria.where("dataDaPublicacao").is(data));
        return Flux.fromIterable(mongoTemplate.find(query, VideoEntityAux.class));
    }

    @Override
    public Flux<VideoEntityAux> obterVideoPorTituloEData(String titulo, LocalDate data) {
        Query query = new Query(Criteria.where("titulo").is(titulo).and("dataDaPublicacao").is(data));
        return Flux.fromIterable(mongoTemplate.find(query, VideoEntityAux.class));
    }

    // ADICIONA CURTIDA AO VIDEO
    @Override
    public Mono<Void> adicionarCurtida(String videoId, CurtidaEntity curtida) {
        return ivideoRepository.findById(videoId)
                .flatMap(videoEntityAux -> {
                    // Adiciona a curtida no vídeo
                    videoEntityAux.adicionarCurtida(new CurtidaEntityAux(curtida.getId(), curtida.getValor()));
                    // Salva o vídeo atualizado no repositório
                    return ivideoRepository.save(videoEntityAux).then();
                });
    }

    @Override
    public Flux<VideoEntityAux> obterVideosCurtidasDescendente() {
        Query query = new Query().with(Sort.by(Sort.Order.desc("totalCurtidas")));
        return Flux.fromIterable(mongoTemplate.find(query, VideoEntityAux.class));
    }

    @Override
    public Flux<VideoEntityAux> obterVideosTop(Integer limit) {
        Query query = new Query().with(Sort.by(Sort.Order.desc("totalCurtidas"))).limit(limit);
        return Flux.fromIterable(mongoTemplate.find(query, VideoEntityAux.class));
    }
}
