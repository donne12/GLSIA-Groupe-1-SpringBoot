package com.glsia.groupe1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "article")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private int qteStok;
    private int qteSeuil;
    private double prix;
    private LocalDate dateCreation;
    @ManyToOne()
    @JoinColumn(name = "categoryId", insertable = false ,updatable = false)
    private Categorie Categorie;
    private int categoryId;
}
