package com.tc4.streaming.infrastructure.persistence;

import com.tc4.streaming.entities.VideoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVideoRepository extends ReactiveMongoRepository<VideoEntityAux, String> {
    //Flux<VideoEntityAux>> findAll(Pageable pageable);

    //PAGINAÇÃO
    Flux<VideoEntityAux> findAll(Sort sort);

}
