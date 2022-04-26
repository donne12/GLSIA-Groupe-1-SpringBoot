package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.service.ArticleService;
import com.glsia.groupe1.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vente")
@CrossOrigin
public class VenteController {

    @Autowired
    private VenteService venteService;
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vente>> getAllVente(){
        List<Vente> ventes = venteService.showAll();
        return new ResponseEntity<>(ventes, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vente> getOneVente(@PathVariable("id") int id){
        Vente vente = venteService.find(id);
        return new ResponseEntity<>(vente, HttpStatus.OK);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vente> addOneVente(){
        Vente vente = null;
        vente.setTotal(0);
        vente.setDateVente(LocalDate.now());
        venteService.save(vente);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vente> updateVente(@RequestBody Vente vente){
        venteService.save(vente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Article> deleteVente(@PathVariable("id") int id){
        Vente vente = venteService.find(id);
        venteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
