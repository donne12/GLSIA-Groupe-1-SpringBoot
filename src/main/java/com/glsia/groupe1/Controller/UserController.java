package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.User;
import com.glsia.groupe1.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUser(){
       /* List<User> userList = userService.showAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);*/
        return  ResponseEntity.ok().body(userService.showAll());
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getOneUser(@PathVariable("id") int id){
        User user = userService.find(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addOneUser(@RequestBody User user){
       /* userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);*/
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/add").toUriString());
        return  ResponseEntity.created(uri).body(userService.save(user));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user){
       /* userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);*/
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/update").toUriString());
        return  ResponseEntity.created(uri).body(userService.save(user));
    }

    @PutMapping(value = "/addtouser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Data
    class RoleToUserForm{
        private String username;
        private String rolename;
    }


}
