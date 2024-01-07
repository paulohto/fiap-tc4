package com.tc4.streaming.application.repository;

import com.tc4.streaming.entities.VideoEntity;
import reactor.core.publisher.Mono;

public interface IVideoRepository {
    Mono<VideoEntity> save(VideoEntity video);
    Mono<VideoEntity> findByCategory(String category);
}
