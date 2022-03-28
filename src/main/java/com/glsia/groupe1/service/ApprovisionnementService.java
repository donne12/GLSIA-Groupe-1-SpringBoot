package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Approvisionnement;
import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.repository.ApprovisionnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovisionnementService {
    @Autowired
    private ApprovisionnementRepository approvisionnementRepository;

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
