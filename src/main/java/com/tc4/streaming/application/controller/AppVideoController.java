package com.tc4.streaming.application.controller;

import com.tc4.streaming.application.service.VideoServiceImpl;
import com.tc4.streaming.entities.VideoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/videos")
public class AppVideoController {
    private final VideoServiceImpl videoService;

    @Autowired
    public AppVideoController(VideoServiceImpl videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/gravar")
    public Mono<VideoEntity> gravarVideo(@RequestBody VideoEntity video) {
        return videoService.saveVideo(video);
    }
}
