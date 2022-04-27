package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Role;
import com.glsia.groupe1.models.User;
import com.glsia.groupe1.repository.RoleRepository;
import com.glsia.groupe1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){

            throw new UsernameNotFoundException("User not found in database");
        }else {

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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

    public void addRoleToUser(String username, String name){
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(name);
        user.getRoles().add(role);
    }


}
