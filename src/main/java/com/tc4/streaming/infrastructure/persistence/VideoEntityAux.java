package com.tc4.streaming.infrastructure.persistence;

import com.tc4.streaming.entities.VideoEntity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "videos")
public class VideoEntityAux {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private LocalDate dataDaPublicacao;
    private String categoria;
    //private Integer gostei = 1;


    // Construtor sem argumentos
    public VideoEntityAux() {
    }

    public VideoEntityAux(
            String id,
            //ObjectId id,
            String titulo,
            String descricao,
            String url,
            LocalDate dataDaPublicacao,
            String categoria
    )
    {
        if (id == null && titulo == null && descricao == null && url == null && dataDaPublicacao == null && categoria == null) {

        }
        else if(id == null || id.isEmpty() || titulo == null || titulo.isEmpty() || descricao.isEmpty() || url.isEmpty() || dataDaPublicacao == null || categoria.isEmpty()){
            throw new IllegalArgumentException("Campos não podem ser vazios.");
        }

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataDaPublicacao = dataDaPublicacao;
        this.categoria = categoria;
        //this.gostei = gostei;
    }



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public LocalDate getDataDaPublicacao() {
        return this.dataDaPublicacao;
    }

    public String getCategoria() {
        return this.categoria;
    }

    // FAVORITAR - GOSTEI //
//    public Integer getGostei() {
//        return gostei;
//    }
//
//    public void setGostei(Integer gostei) {
//        this.gostei = gostei;
//    }

}
