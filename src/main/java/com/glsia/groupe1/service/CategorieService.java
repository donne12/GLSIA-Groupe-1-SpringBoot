package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

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
}
