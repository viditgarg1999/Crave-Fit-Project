package com.cravefit.cravefit.dto.cart;

import com.cravefit.cravefit.entities.Product;

import java.util.Date;

public class GetCartResponseModel {

    Long cartId;
    Product product;
    int quantity;
    Date createdDate;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public GetCartResponseModel() {
    }

    public GetCartResponseModel(Long cartId, Product product, int quantity, Date createdDate) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
        this.createdDate = createdDate;
    }
}
