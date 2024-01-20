package com.tc4.streaming.entities;

import java.time.LocalDateTime;

public class VideoEntity {
    //private final String id;
    private final String titulo;
    private final String descricao;
    private final String url;
    private final LocalDateTime dataDaPublicacao;
    private final String categoria;
    private final String id;
    private Integer gostei = 1;

    public VideoEntity(
            String id,
            String titulo,
            String descricao,
            String url,
            LocalDateTime dataDaPublicacao,
            String categoria
    )
    {
        if(titulo.isEmpty() || descricao.isEmpty() || url.isEmpty() || dataDaPublicacao == null || categoria.isEmpty()){
            throw new IllegalArgumentException("Campos não podem ser vazios.");
        }

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataDaPublicacao = dataDaPublicacao;
        this.categoria = categoria;
        this.gostei = gostei;
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

    // FAVORITAR - GOSTEI //
    public Integer getGostei() {
        return gostei;
    }

    public void setGostei(Integer gostei) {
        this.gostei = gostei;
    }

    public String getId() {
        return id;
    }
}
