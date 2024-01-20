package com.tc4.streaming.infrastructure.gateway;

import com.tc4.streaming.entities.VideoEntity;
import com.tc4.streaming.infrastructure.persistence.VideoEntityAux;

public class VideoEntityAuxMapper {
    VideoEntityAux toEntity(VideoEntity videoDomainObj){
        return new VideoEntityAux(
                //videoDomainObj.getId(),
                videoDomainObj.getTitulo(),
                videoDomainObj.getDescricao(),
                videoDomainObj.getUrl(),
                videoDomainObj.getDataDaPublicacao(),
                videoDomainObj.getCategoria()
        );
    }

    VideoEntity toDomainObj(VideoEntityAux videoEntityAux){
        return new VideoEntity(
                videoEntityAux.getId(),
                videoEntityAux.getTitulo(),
                videoEntityAux.getDescricao(),
                videoEntityAux.getUrl(),
                videoEntityAux.getDataDaPublicacao(),
                videoEntityAux.getCategoria()
        );
    }
}
