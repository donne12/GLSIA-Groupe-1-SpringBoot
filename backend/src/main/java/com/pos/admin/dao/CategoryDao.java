package com.pos.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.admin.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category,Long>{

}
