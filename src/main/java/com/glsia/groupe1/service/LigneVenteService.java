package com.glsia.groupe1.service;

import com.glsia.groupe1.models.LigneVente;
import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.repository.LigneVenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneVenteService {
    @Autowired
    private LigneVenteRepository ligneVenteRepository;

    public void save(LigneVente ligneVente){
        ligneVenteRepository.save(ligneVente);
    }

    public List<LigneVente> showAll(){
        return ligneVenteRepository.findAll();
    }

    public  LigneVente find(int id){
        Optional<LigneVente> optional = ligneVenteRepository.findById(id);
        LigneVente ligneVente = null;
        if(optional.isPresent())
        {
            ligneVente = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable");
        }
        return ligneVente;
    }

    public void delete(int id){
        ligneVenteRepository.deleteById(id);
    }
}
