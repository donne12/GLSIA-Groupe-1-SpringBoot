package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Approvisionnement;
import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.models.VenteArticle;
import com.glsia.groupe1.repository.ApprovisionnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class ApprovisionnementService {
    @Autowired
    private ApprovisionnementRepository approvisionnementRepository;
    @Autowired
    private EntityManager entityManager;

    public void save(Approvisionnement approvisionnement){
        approvisionnementRepository.save(approvisionnement);
    }

    public List<Approvisionnement> showAll(){
        return approvisionnementRepository.findAll();
    }

    public  Approvisionnement find(int id){
        Optional<Approvisionnement> optional = approvisionnementRepository.findById(id);
        Approvisionnement approvisionnement = null;
        if(optional.isPresent())
        {
            approvisionnement = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable");
        }
        return  approvisionnement;
    }

    public void delete(int id){
        approvisionnementRepository.deleteById(id);
    }



}
