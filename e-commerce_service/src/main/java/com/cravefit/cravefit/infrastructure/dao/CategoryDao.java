package com.cravefit.cravefit.infrastructure.dao;

import com.cravefit.cravefit.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CategoryDao extends JpaRepository<ProductCategory,Long> {

    @Query(value = "select categoryId from ProductCategory p where p.name=?1")
    Long getIdFromName(String categoryName);

}
