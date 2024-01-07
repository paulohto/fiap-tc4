package com.tc4.streaming.application.service;

import com.tc4.streaming.application.repository.IVideoRepository;
import com.tc4.streaming.entities.VideoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VideoServiceImpl implements IVideoService{
    private final IVideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(IVideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Mono<VideoEntity> saveVideo(VideoEntity video) {
        // Lógica de validação ou processamento adicional, se necessário
        return videoRepository.save(video);
    }

    @Override
    public Mono<VideoEntity> getVideoByCategory(String category) {
        // Lógica de busca por categoria, possivelmente usando videoRepository.findByCategory(category)
        return null;
    }
}
