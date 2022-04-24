package com.glsia.groupe1.repository;

import com.glsia.groupe1.models.VenteArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteArticleRepository extends JpaRepository<VenteArticle, Integer> {

}
