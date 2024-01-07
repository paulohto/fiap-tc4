package com.tc4.streaming.application.repository;

import com.tc4.streaming.entities.VideoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoDBVideoRepository implements IVideoRepository{

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public MongoDBVideoRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<VideoEntity> save(VideoEntity video) {
        return reactiveMongoTemplate.save(video);
    }

    @Override
    public Mono<VideoEntity> findByCategory(String category) {
        // Implemente a lógica de busca por categoria no MongoDB
        return null;
    }
}
