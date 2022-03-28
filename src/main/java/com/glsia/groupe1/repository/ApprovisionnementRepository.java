package com.glsia.groupe1.repository;

import com.glsia.groupe1.models.Approvisionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovisionnementRepository extends JpaRepository<Approvisionnement, Integer> {
}
