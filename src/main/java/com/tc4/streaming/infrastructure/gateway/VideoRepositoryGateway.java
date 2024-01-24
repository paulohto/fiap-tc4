package com.tc4.streaming.infrastructure.gateway;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.IVideoRepository;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VideoRepositoryGateway implements IVideoGateway {

    private final IVideoRepository ivideoRepository;
    private final VideoEntityAuxMapper videoEntityAuxMapper;

    public VideoRepositoryGateway(IVideoRepository ivideoRepository, VideoEntityAuxMapper videoEntityAuxMapper) {
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
    }

    @Override
    public Mono<Void> apagarVideo(String videoId) {
        return ivideoRepository.deleteById(videoId);
    }

}
