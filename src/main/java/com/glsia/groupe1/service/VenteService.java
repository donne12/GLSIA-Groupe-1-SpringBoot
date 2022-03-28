package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.repository.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;

    public void save(Vente vente){
        venteRepository.save(vente);
    }

    public List<Vente> showAll(){
        return venteRepository.findAll();
    }

    public  Vente find(int id){
        Optional<Vente> optional = venteRepository.findById(id);
        Vente vente = null;
        if(optional.isPresent())
        {
            vente = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable");
        }
        return vente;
    }

    public void delete(int id){
        venteRepository.deleteById(id);
    }

}
