package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Role;
import com.glsia.groupe1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public void save(Role role){
        roleRepository.save(role);
    }

    public List<Role> showAll(){
        return roleRepository.findAll();
    }

    public  Role find(int id){
        Optional<Role> optional = roleRepository.findById(id);
        Role role = null;
        if(optional.isPresent())
        {
            role = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable de l'utilisateur");
        }
        return  role;
    }

    public void delete(int id){
        roleRepository.deleteById(id);
    }
}
