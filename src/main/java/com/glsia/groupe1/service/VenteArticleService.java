package com.glsia.groupe1.service;


import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.models.VenteArticle;
import com.glsia.groupe1.repository.VenteArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class VenteArticleService {
    @Autowired
    private VenteArticleRepository venteArticleRepository;
    @Autowired
    private EntityManager entityManager;

    public void save(VenteArticle venteArticle){
        venteArticleRepository.save(venteArticle);
    }

    public List<VenteArticle> showAll(){
        return venteArticleRepository.findAll();
    }

    public  VenteArticle find(int id){
        Optional<VenteArticle> optional = venteArticleRepository.findById(id);
        VenteArticle venteArticle = null;
        if(optional.isPresent())
        {
            venteArticle = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable");
        }
        return venteArticle;
    }

    public void delete(int id){
        venteArticleRepository.deleteById(id);
    }

    public List<VenteArticle> showByVenteId(int id){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<VenteArticle> query = builder.createQuery(VenteArticle.class);
        Root<VenteArticle> i = query.from(VenteArticle.class);
        query.select(i);
        query.where(builder.equal(i.get("venteId").as(int.class), id));

        List<VenteArticle> ventes = entityManager.createQuery(query).getResultList();

        return ventes;
    }

}
