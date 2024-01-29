package com.tc4.streaming.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICurtidaRepository extends ReactiveMongoRepository<CurtidaEntityAux, String> {

}
