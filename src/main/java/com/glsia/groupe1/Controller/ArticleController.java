package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticle(){
        List<Article> articles = articleService.showAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Article> getOneArticle(@PathVariable("id") int id){
        Article article = articleService.find(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Article> addOneArticle(@RequestBody Article article){
        article.setDateCreation(LocalDate.now());
        articleService.save(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article){
        articleService.save(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") int id){
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
