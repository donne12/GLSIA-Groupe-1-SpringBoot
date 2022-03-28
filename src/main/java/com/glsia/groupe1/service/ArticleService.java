package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.repository.ArticleReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleReposytory articleReposytory;

    public void save(Article article){
        articleReposytory.save(article);
    }

    public List<Article> showAll(){
        return articleReposytory.findAll();
    }

    public  Article find(int id){
        Optional<Article> optional = articleReposytory.findById(id);
        Article article = null;
        if(optional.isPresent())
        {
            article = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable");
        }
        return  article;
    }

    public void delete(int id){
        articleReposytory.deleteById(id);
    }
}
