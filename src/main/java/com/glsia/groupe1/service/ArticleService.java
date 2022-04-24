package com.glsia.groupe1.service;

import com.glsia.groupe1.models.Approvisionnement;
import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.repository.ArticleReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleReposytory articleReposytory;
    @Autowired
    private EntityManager entityManager;

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

    public List<Article> search(String libelle){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Article> query = builder.createQuery(Article.class);
        Root<Article> i = query.from(Article.class);
        query.select(i);
        query.where(builder.like(i.get("libelle").as(String.class), "%"+libelle+"%"));

        List<Article> articles = entityManager.createQuery(query).getResultList();

        return articles;
    }
}
