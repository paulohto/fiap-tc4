package com.tc4.streaming.main;

import com.tc4.streaming.adapters.gateways.IVideoGateway;
import com.tc4.streaming.infrastructure.controllers.VideoDTOMapper;
import com.tc4.streaming.infrastructure.gateway.VideoEntityAuxMapper;
import com.tc4.streaming.infrastructure.gateway.VideoRepositoryGateway;
import com.tc4.streaming.infrastructure.persistence.IVideoRepository;
import com.tc4.streaming.usercases.VideoCrudUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class VideoConfig {
    @Bean
    VideoCrudUseCase criarVideo(IVideoGateway ivideoGateway){
        return new VideoCrudUseCase(ivideoGateway);
    }

    @Bean
    IVideoGateway ivideoGateway(MongoTemplate mongoTemplate, IVideoRepository ivideoRepository, VideoEntityAuxMapper videoEntityAuxMapper){
        return new VideoRepositoryGateway(mongoTemplate, ivideoRepository, videoEntityAuxMapper);
    }

    @Bean
    VideoEntityAuxMapper videoEntityAuxMapper() {
        return new VideoEntityAuxMapper();
    }

    @Bean
    VideoDTOMapper videoDTOMapper() {
        return new VideoDTOMapper();
    }
}
