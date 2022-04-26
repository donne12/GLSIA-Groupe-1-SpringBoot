package com.glsia.groupe1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double total;
    private LocalDate dateVente;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVenteList;


}
