package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Role;
import com.glsia.groupe1.models.User;
import com.glsia.groupe1.repository.RoleRepository;
import com.glsia.groupe1.service.RoleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getAllRole(){
        return  ResponseEntity.ok().body(roleService.showAll());
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getOneRole(@PathVariable("id") int id){
        Role role = roleService.find(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> addOneRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/add").toUriString());
        return  ResponseEntity.created(uri).body(roleService.save(role));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/update").toUriString());
        return  ResponseEntity.created(uri).body(roleService.save(role));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable("id") int id){
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
