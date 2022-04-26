package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.User;
import com.glsia.groupe1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public List<User> showAll(){
        return userRepository.findAll();
    }

    public  User find(int id){
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if(optional.isPresent())
        {
            user = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable de l'utilisateur");
        }
        return  user;
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }
}
