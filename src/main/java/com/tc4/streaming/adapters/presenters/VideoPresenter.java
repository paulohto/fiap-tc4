package com.tc4.streaming.adapters.presenters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc4.streaming.entities.VideoEntity;
import jdk.security.jarsigner.JarSignerException;

public class VideoPresenter {
    public static String ToJason(VideoEntity video) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        return mapper.writeValueAsString(video);
    }
}
