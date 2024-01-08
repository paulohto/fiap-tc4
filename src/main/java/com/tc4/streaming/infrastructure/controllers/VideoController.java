package com.tc4.streaming.infrastructure.controllers;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.usercases.VideoCrudUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private VideoCrudUseCase videoCrudUseCase;
    private VideoDTOMapper videoDTOMapper;

    public VideoController(VideoCrudUseCase videoCrudUseCase, VideoDTOMapper videoDTOMapper) {
        this.videoCrudUseCase = videoCrudUseCase;
        this.videoDTOMapper = videoDTOMapper;
    }

    @PostMapping
    Mono<CreateVideoResponse> create(@RequestBody CreateVideoRequest request){
        VideoEntity videoEntityBusinessObj = videoDTOMapper.toVideoEntity(request);
        return videoCrudUseCase.criarVideo(videoEntityBusinessObj)
                .map(videoDTOMapper::toResponse);
    }


//    @PostMapping
//    CreateVideoResponse create(@RequestBody CreateVideoRequest request){
//        VideoEntity videoEntityBusinessObj = videoDTOMapper.toVideoEntity(request);
//        VideoEntity videoEntity = videoCrudUseCase.criarVideo(videoEntityBusinessObj);
//        return videoDTOMapper.toResponse(videoEntity);
//    }
}
