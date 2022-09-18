package com.cravefit.cravefit.infrastructure.dao;

import com.cravefit.cravefit.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductDao extends JpaRepository<Product,Long> {

    //  Order by popularity
    @Query(value = "from Product p where p.categoryId=?1 order by p.popularityIndex")
    Page<Product> getFromCategoryFilter1(Long categoryId, Pageable perPageable);

    // Order by Low to High price
    @Query(value = "from Product p where p.categoryId=?1 order by p.price")
    Page<Product> getFromCategoryFilter2(Long categoryId, Pageable perPageable);

    // Order by High to low price
    @Query(value = "from Product p where p.categoryId=?1 order by p.price desc")
    Page<Product> getFromCategoryFilter3(Long categoryId, Pageable perPageable);

    // Order by What's new
    @Query(value = "from Product p where p.categoryId=?1order by p.listedDate desc")
    Page<Product> getFromCategoryFilter4(Long categoryId, Pageable perPageable);

    //  Order by popularity
    @Query(value = "from Product p where lower(p.name) like %?1% or lower(p.tags) like %?1% order by p.popularityIndex")
    Page<Product> getFromSearchParameterFilter1(String keyword, Pageable perPageable);

    // Order by Low to High price
    @Query(value = "from Product p where lower(p.name) like %?1% or lower(p.tags) like %?1% order by p.price")
    Page<Product> getFromSearchParameterFilter2(String keyword, Pageable perPageable);

    // Order by High to low price
    @Query(value = "from Product p where lower(p.name) like %?1% or lower(p.tags) like %?1% order by p.price desc")
    Page<Product> getFromSearchParameterFilter3(String keyword, Pageable perPageable);

    // Order by What's new
    @Query(value = "from Product p where lower(p.name) like %?1% or lower(p.tags) like %?1% order by p.listedDate desc")
    Page<Product> getFromSearchParameterFilter4(String keyword, Pageable perPageable);

}
