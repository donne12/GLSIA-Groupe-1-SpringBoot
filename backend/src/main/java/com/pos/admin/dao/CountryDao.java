package com.pos.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pos.admin.entity.Country;

public interface CountryDao extends JpaRepository<Country,Long>{

}
