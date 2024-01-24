package com.tc4.streaming.infrastructure.persistence;

import com.tc4.streaming.entities.VideoEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

public interface IVideoRepository extends ReactiveMongoRepository<VideoEntityAux, String> {
    //Mono<VideoEntity> insert(String );
    //Mono<Void> deleteById(String id);
}
