package com.tc4.streaming.entities;

import java.util.ArrayList;

public class ListaDeVideosEntity {
    //private final VideoEntity video;
    private final ArrayList<VideoEntity> videos;

    public ListaDeVideosEntity(ArrayList<VideoEntity> videos) {
        this.videos = videos;
    }

    public ArrayList<VideoEntity> getVideos() {
        return videos;
    }
}
