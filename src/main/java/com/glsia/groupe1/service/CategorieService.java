package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private EntityManager entityManager;

    public void save(Categorie categorie){
        categorieRepository.save(categorie);
    }

    public List<Categorie> showAll(){
        return categorieRepository.findAll();
    }
    public  Categorie find(int id){
        Optional<Categorie> optional = categorieRepository.findById(id);
        Categorie categorie = null;
        if(optional.isPresent())
        {
            categorie = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable");
        }
        return  categorie;
    }

    public void delete(int id){
        categorieRepository.deleteById(id);
    }
    public List<Categorie> search(String libelle){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Categorie> query = builder.createQuery(Categorie.class);
        Root<Categorie> i = query.from(Categorie.class);
        query.select(i);
        query.where(builder.like(i.get("libelle").as(String.class), "%"+libelle+"%"));

        List<Categorie> categories = entityManager.createQuery(query).getResultList();

        return categories;
    }
}
