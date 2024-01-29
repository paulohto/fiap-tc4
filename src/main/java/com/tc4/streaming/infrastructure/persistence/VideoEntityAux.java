package com.tc4.streaming.infrastructure.persistence;

import com.tc4.streaming.entities.VideoEntity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "videos")
public class VideoEntityAux {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private LocalDate dataDaPublicacao;
    private String categoria;

    private List<CurtidaEntityAux> curtidas;
    private Integer totalCurtidas;  // Atributo para armazenar o total de curtidas

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

    // BLOCO CURTIDAS
    public void setCurtidas(List<CurtidaEntityAux> curtidas) {
        this.curtidas = curtidas;
    }

    public void setTotalCurtidas(Integer totalCurtidas) {
        this.totalCurtidas = totalCurtidas;
    }

    // Método para adicionar curtida
    public void adicionarCurtida(CurtidaEntityAux curtida) {
        // Adicionar a curtida à lista
        if (curtidas == null) {
            curtidas = new ArrayList<>();
        }
        curtidas.add(curtida);

        // Incrementar o total de curtidas
        if (totalCurtidas == null) {
            totalCurtidas = 0;
        }
        totalCurtidas++;

        // Adicionar logs
        System.out.println("Curtida adicionada. Total de curtidas agora: " + totalCurtidas);
    }

    // Método para calcular o total de curtidas
    public Integer getTotalCurtidas() {
        return curtidas != null ? curtidas.size() : 0;
    }


}
