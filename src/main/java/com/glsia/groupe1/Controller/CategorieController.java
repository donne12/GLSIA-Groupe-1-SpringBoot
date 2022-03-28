package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorie")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping("/all")
    public ResponseEntity<List<Categorie>> getAllCategorie(){
        List<Categorie> categories = categorieService.showAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Categorie> getOneCategorie(@PathVariable("id") int id){
        Categorie categories = categorieService.find(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Categorie> addOneCategorie(@RequestBody Categorie categorie){
        categorieService.save(categorie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
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
