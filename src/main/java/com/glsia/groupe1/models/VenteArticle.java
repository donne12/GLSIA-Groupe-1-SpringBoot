package com.glsia.groupe1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "vente_article")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VenteArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "venteId",insertable = false,updatable = false)
    private Vente vente;
    private int venteId;

    @ManyToOne
    @JoinColumn(name = "articleId",insertable = false,updatable = false)
    private Article article;
    private int articleId;

}




