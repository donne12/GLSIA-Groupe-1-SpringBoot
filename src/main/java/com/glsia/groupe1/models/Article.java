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
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Categorie Categorie;
    private int categoryId;

    public int getQteSeuil() {
        return qteSeuil;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getQteStok() {
        return qteStok;
    }

    public void setQteStok(int qteStok) {
        this.qteStok = qteStok;
    }

    public void setQteSeuil(int qteSeuil) {
        this.qteSeuil = qteSeuil;
    }
}
