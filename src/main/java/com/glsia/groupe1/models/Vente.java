package com.glsia.groupe1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean cloture;
    private double mt;
    private LocalDate dateVente;

    @OneToMany(mappedBy = "venteId")
    List<VenteArticle> venteArticleSet;
}
