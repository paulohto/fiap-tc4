package com.tc4.streaming.entities;

import java.time.LocalDateTime;

public class VideoEntity {

    private final String id;

    private final String titulo;
    private final String descricao;
    private final String url;
    private final LocalDateTime dataDaPublicacao;
    private final String categoria;

    public VideoEntity(
            String id,
            String titulo,
            String descricao,
            String url,
            LocalDateTime dataDaPublicacao,
            String categoria
            //Integer gostei
    )
    {
        if(titulo == null || titulo.isEmpty() || descricao.isEmpty() || url.isEmpty() || dataDaPublicacao == null || categoria.isEmpty()){
            throw new IllegalArgumentException("Campos não podem ser vazios.");
        }

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataDaPublicacao = dataDaPublicacao;
        this.categoria = categoria;
        this.gostei = 1;
    }


    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getUrl() {
        return this.url;
    }

    public LocalDateTime getDataDaPublicacao() {
        return this.dataDaPublicacao;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getId() {
        return id;
    }

    // Construtor de cópia
    public VideoEntity(VideoEntity original) {
        this(
                original.getId(),
                original.getTitulo(),
                original.getDescricao(),
                original.getUrl(),
                original.getDataDaPublicacao(),
                original.getCategoria()
                //original.getGostei()
        );
    }
}
