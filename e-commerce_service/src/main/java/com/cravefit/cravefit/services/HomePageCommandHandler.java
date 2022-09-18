package com.cravefit.cravefit.services;

import com.cravefit.cravefit.dto.homepage.GetProductResponseModel;
import com.cravefit.cravefit.dto.homepage.ProductResponseDetails;
import com.cravefit.cravefit.entities.Product;
import com.cravefit.cravefit.infrastructure.dao.CategoryDao;
import com.cravefit.cravefit.infrastructure.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomePageCommandHandler {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    // Get Products from a specific Category
    public ResponseEntity<?> getCategory(Long categoryId, int page, int sortBy) {

        try{

            Pageable pageable = PageRequest.of(page,20);           // 20 Records Per page


            Page<Product> productList =null;

            if(sortBy==1)
                productList =  productDao.getFromCategoryFilter1(categoryId,pageable);

            else if(sortBy==2)
                productList =  productDao.getFromCategoryFilter2(categoryId,pageable);

            else if(sortBy==3)
                productList =  productDao.getFromCategoryFilter3(categoryId,pageable);

            else
                productList =  productDao.getFromCategoryFilter4(categoryId,pageable);



            List<ProductResponseDetails> productResponseDetailsList = new ArrayList<>();

            for(Product record:productList)
            {
                ProductResponseDetails productResponseDetails = new ProductResponseDetails(record.getProductId(), record.getName(), record.getImageUrl(), record.getPrice(), record.getDescription());
                productResponseDetailsList.add(productResponseDetails);
            }

            GetProductResponseModel getProductResponseModel = new GetProductResponseModel(page,productList.getTotalPages(),productResponseDetailsList);

            return ResponseEntity.ok(getProductResponseModel);
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

    public ResponseEntity<?> searchQuery(String keyword, int page, int sortBy) {

        try{

            Pageable pageable = PageRequest.of(page,20);           // 20 Records Per page
            Page<Product> productList =null;

            if(sortBy==1)
                productList =  productDao.getFromSearchParameterFilter1(keyword,pageable);

            else if(sortBy==2)
                productList =  productDao.getFromSearchParameterFilter2(keyword,pageable);

            else if(sortBy==3)
                productList =  productDao.getFromSearchParameterFilter3(keyword,pageable);

            else
                productList =  productDao.getFromSearchParameterFilter4(keyword,pageable);



            List<ProductResponseDetails> productResponseDetailsList = new ArrayList<>();

            for(Product record:productList)
            {
                ProductResponseDetails productResponseDetails = new ProductResponseDetails(record.getProductId(), record.getName(), record.getImageUrl(), record.getPrice(), record.getDescription());
                productResponseDetailsList.add(productResponseDetails);
            }

            GetProductResponseModel getProductResponseModel = new GetProductResponseModel(page,productList.getTotalPages(),productResponseDetailsList);

            return ResponseEntity.ok(productResponseDetailsList);
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }
}
