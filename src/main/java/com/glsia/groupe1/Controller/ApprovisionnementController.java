package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Approvisionnement;
import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.service.ApprovisionnementService;
import com.glsia.groupe1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/approv")
@CrossOrigin
public class ApprovisionnementController {
    @Autowired
    private ApprovisionnementService approvisionnementService;
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Approvisionnement>> getAllApprov(){
        List<Approvisionnement> approvisionnements = approvisionnementService.showAll();
        return new ResponseEntity<>(approvisionnements, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Approvisionnement> getOneApprov(@PathVariable("id") int id){
        Approvisionnement approvisionnement = approvisionnementService.find(id);
        return new ResponseEntity<>(approvisionnement, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Approvisionnement> addOneApprov(@RequestBody Approvisionnement approvisionnement){
        approvisionnement.setDateApprov(LocalDate.now());
        approvisionnementService.save(approvisionnement);
        int article_key = approvisionnement.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() + approvisionnement.getQuantite());
        articleService.save(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Approvisionnement> updateApprov(@RequestBody Approvisionnement approvisionnement){
        approvisionnementService.save(approvisionnement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Approvisionnement> deleteApprov(@PathVariable("id") int id){
        Approvisionnement approvisionnement = approvisionnementService.find(id);
        int article_key = approvisionnement.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() - approvisionnement.getQuantite());
        approvisionnementService.delete(id);
        articleService.save(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
