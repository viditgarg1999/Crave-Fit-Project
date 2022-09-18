package com.cravefit.cravefit.controller;

import com.cravefit.cravefit.services.HomePageCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/ecommerce/homepage")
public class HomePageController {

    @Autowired
    private HomePageCommandHandler homePageCommandHandler;


    /*
    Sort By -
    1. Popularity
    2. Price low to high
    3. Price High to low
    4. Whats New
     */

    @GetMapping("/search-query/{keyword}/{page}/{sortBy}")
    public ResponseEntity<?> searchQuery(@PathVariable String keyword, @PathVariable Integer page, @PathVariable Integer sortBy)
    {
        return homePageCommandHandler.searchQuery(keyword.toLowerCase(),page,sortBy);
    }

    @GetMapping("/get-category/{categoryId}/{page}/{sortBy}")
    public ResponseEntity<?> getCategory(@PathVariable Long categoryId, @PathVariable Integer page, @PathVariable Integer sortBy)
    {
        return homePageCommandHandler.getCategory(categoryId,page, sortBy);
    }


}
