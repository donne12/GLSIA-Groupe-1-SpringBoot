package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.repository.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;
    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Vente> showActive(){
        boolean booll = false;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Vente> query = builder.createQuery(Vente.class);
        Root<Vente> i = query.from(Vente.class);
        query.select(i);
        query.where(builder.equal(i.get("cloture").as(boolean.class), booll));

        List<Vente> ventes = entityManager.createQuery(query).getResultList();

        return ventes;
    }

}
