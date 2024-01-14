package com.tc4.streaming.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Document(collection = "videos")
public class VideoEntityAux {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private LocalDateTime dataDaPublicacao;
    private String categoria;
    private Integer gostei = 1;

    public VideoEntityAux(){}

    public VideoEntityAux(
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

}
