package com.cravefit.cravefit.dto.cart;

public class AddCartProductResponseModel {

    private Long cartId;

    public AddCartProductResponseModel() {
    }

    public AddCartProductResponseModel(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
