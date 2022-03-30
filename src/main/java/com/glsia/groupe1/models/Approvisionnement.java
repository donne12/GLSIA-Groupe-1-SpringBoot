package com.glsia.groupe1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "approvisionnement")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Approvisionnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantite;
    private LocalDate dateApprov;

    @ManyToOne
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    private Article article;
    private int articleId;
}
