package com.cravefit.cravefit.dto.cart;

public class UpdateQuantityRequestModel {

    private Long cartId;
    private int quantity;

    public UpdateQuantityRequestModel() {
    }

    public UpdateQuantityRequestModel(Long cartId, int quantity) {
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
