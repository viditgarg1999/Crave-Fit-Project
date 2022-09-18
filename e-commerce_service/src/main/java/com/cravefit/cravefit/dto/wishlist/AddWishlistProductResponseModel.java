package com.cravefit.cravefit.dto.wishlist;

public class AddWishlistProductResponseModel {

    private Long wislistId;

    public AddWishlistProductResponseModel() {
    }

    public AddWishlistProductResponseModel(Long wislistId) {
        this.wislistId = wislistId;
    }

    public Long getWislistId() {
        return wislistId;
    }

    public void setWislistId(Long wislistId) {
        this.wislistId = wislistId;
    }
}
