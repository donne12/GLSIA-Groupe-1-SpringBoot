package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie")
@CrossOrigin
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categorie>> getAllCategorie(){
        List<Categorie> categories = categorieService.showAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> getOneCategorie(@PathVariable("id") int id){
        Categorie categories = categorieService.find(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> addOneCategorie(@RequestBody Categorie categorie){
        categorieService.save(categorie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> updateCategorie(@RequestBody Categorie categorie){
        categorieService.save(categorie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Categorie> deleteCategorie(@PathVariable("id") int id){
        categorieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
