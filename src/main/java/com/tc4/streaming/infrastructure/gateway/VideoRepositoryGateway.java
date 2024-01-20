package com.tc4.streaming.infrastructure.gateway;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.IVideoRepository;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VideoRepositoryGateway implements IVideoGateway {

//    private final MongoTemplate mongoTemplate;


    private final IVideoRepository ivideoRepository;
    private final VideoEntityAuxMapper videoEntityAuxMapper;

    public VideoRepositoryGateway(IVideoRepository ivideoRepository, VideoEntityAuxMapper videoEntityAuxMapper) {
//        this.mongoTemplate = mongoTemplate;
        this.ivideoRepository = ivideoRepository;
        this.videoEntityAuxMapper = videoEntityAuxMapper;
    }

    @Override
    public Mono<VideoEntity> criarVideo(VideoEntity videoDomainObj) {
        VideoEntityAux videoEntityAux = videoEntityAuxMapper.toEntity(videoDomainObj);
        return ivideoRepository.save(videoEntityAux)
                .map(videoEntityAuxMapper::toDomainObj);
    }
    @Override
    public Flux<VideoEntityAux> obterTodosVideos(){
        return this.ivideoRepository.findAll();
    }

    @Override
    public Mono<VideoEntityAux> obterVideoPorCodigo(String videoId) {
        return this.ivideoRepository
                .findById(videoId);
                //.orElseThrow(()-> new IllegalArgumentException("Vídeo não encontrado"));
    }

    @Override
    public Mono<Void> apagarVideo(String videoId) {
        return ivideoRepository.deleteById(videoId);
    }

//    @Override
//    public Flux<VideoEntityAux> obterPorCategoria(String categoria) {
//        Query query = new Query(Criteria.where("categoria").in(categoria));
//        return (Flux<VideoEntityAux>) mongoTemplate.find(query, VideoEntityAux.class);
//    }


}
