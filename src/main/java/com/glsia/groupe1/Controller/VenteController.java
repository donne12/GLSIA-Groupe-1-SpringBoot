package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.service.ArticleService;
import com.glsia.groupe1.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vente")
public class VenteController {

    @Autowired
    private VenteService venteService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    public ResponseEntity<List<Vente>> getAllVente(){
        List<Vente> ventes = venteService.showAll();
        return new ResponseEntity<>(ventes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Vente> getOneVente(@PathVariable("id") int id){
        Vente vente = venteService.find(id);
        return new ResponseEntity<>(vente, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Vente> addOneVente(@RequestBody Vente vente){
        vente.setDateVente(LocalDate.now());
        venteService.save(vente);
        int article_key = vente.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() - vente.getQuantite());
        articleService.save(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Vente> updateVente(@RequestBody Vente vente){
        venteService.save(vente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Article> deleteVente(@PathVariable("id") int id){
        Vente vente = venteService.find(id);

        int article_key = vente.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() + vente.getQuantite());
        venteService.delete(id);
        articleService.save(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
