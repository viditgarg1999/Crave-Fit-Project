package com.cravefit.cravefit.dto.homepage;

import java.util.List;

public class GetProductResponseModel {

    private int currentPage;
    private int totalPages;
    private List<ProductResponseDetails> productResponseDetailsList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ProductResponseDetails> getProductResponseDetailsList() {
        return productResponseDetailsList;
    }

    public void setProductResponseDetailsList(List<ProductResponseDetails> productResponseDetailsList) {
        this.productResponseDetailsList = productResponseDetailsList;
    }

    public GetProductResponseModel(int currentPage, int totalPages, List<ProductResponseDetails> productResponseDetailsList) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.productResponseDetailsList = productResponseDetailsList;
    }
}
