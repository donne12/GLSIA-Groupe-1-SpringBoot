package com.glsia.groupe1.repository;

import com.glsia.groupe1.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleReposytory extends JpaRepository<Article,Integer> {
}
