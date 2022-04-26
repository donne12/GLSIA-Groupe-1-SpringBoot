package com.glsia.groupe1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ligne_vente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LigneVente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "articleId",insertable = false,updatable = false)
    private Article article;
    private int articleId;

    @ManyToOne
    @JoinColumn(name = "venteId",insertable = false,updatable = false)
    private Vente vente;
    private int venteId;
}
