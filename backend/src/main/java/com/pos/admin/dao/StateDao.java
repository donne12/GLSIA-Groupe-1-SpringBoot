package com.pos.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pos.admin.entity.State;

public interface StateDao extends JpaRepository<State, Long>{

	
	@Query("SELECT s FROM State s WHERE s.country.id=:countryId")
	List<State> getByCountryId(Long countryId);

}
