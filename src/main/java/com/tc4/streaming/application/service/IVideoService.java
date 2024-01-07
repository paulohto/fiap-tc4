package com.tc4.streaming.application.service;

import com.tc4.streaming.entities.VideoEntity;
import reactor.core.publisher.Mono;

public interface IVideoService {
    Mono<VideoEntity> saveVideo(VideoEntity video);
    Mono<VideoEntity> getVideoByCategory(String category);
}
