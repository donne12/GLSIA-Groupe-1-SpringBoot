package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.LigneVente;
import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.service.ArticleService;
import com.glsia.groupe1.service.LigneVenteService;
import com.glsia.groupe1.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ligneVente")
@CrossOrigin
public class LigneVenteController {

    @Autowired
    private LigneVenteService ligneVenteService;
    @Autowired
    private VenteService venteService;
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LigneVente>> getAllLigneVente(){
        List<LigneVente> ligneVenteList = ligneVenteService.showAll();
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LigneVente> getOneLigneVente(@PathVariable("id") int id){
        LigneVente ligneVente = ligneVenteService.find(id) ;
        return new ResponseEntity<>(ligneVente, HttpStatus.OK);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LigneVente> addOneLigneVente(@RequestBody LigneVente ligneVente){

        ligneVenteService.save(ligneVente);
        int article_key = ligneVente.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() - ligneVente.getQuantite());
        articleService.save(article);
        Vente vente = venteService.find(ligneVente.getVenteId());
        vente.setTotal(vente.getTotal() + ligneVente.getQuantite());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LigneVente> updateLigneVente(@RequestBody LigneVente ligneVente){
        ligneVenteService.save(ligneVente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<LigneVente> deleteVente(@PathVariable("id") int id){
        LigneVente ligneVente = ligneVenteService.find(id);
        int article_key = ligneVente.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() + ligneVente.getQuantite());
        venteService.delete(id);
        articleService.save(article);
        Vente vente = venteService.find(ligneVente.getVenteId());
        vente.setTotal(vente.getTotal() - ligneVente.getQuantite());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
