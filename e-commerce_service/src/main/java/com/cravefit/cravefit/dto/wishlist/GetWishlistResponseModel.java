package com.cravefit.cravefit.dto.wishlist;

import com.cravefit.cravefit.entities.Product;

import java.util.Date;

public class GetWishlistResponseModel {

    Long wishlistId;
    Product product;
    Date createdDate;

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public GetWishlistResponseModel() {
    }

    public GetWishlistResponseModel(Long wishlistId, Product product, Date createdDate) {
        this.wishlistId = wishlistId;
        this.product = product;
        this.createdDate = createdDate;
    }
}
