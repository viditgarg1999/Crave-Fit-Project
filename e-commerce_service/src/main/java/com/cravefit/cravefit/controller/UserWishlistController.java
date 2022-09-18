package com.cravefit.cravefit.controller;

import com.cravefit.cravefit.dto.wishlist.AddWishlistProductRequestModel;
import com.cravefit.cravefit.services.UserWishlistCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce/wishlist")
public class UserWishlistController {


    @Autowired
    private UserWishlistCommandHandler userWishlistCommandHandler;

    @GetMapping("/get-wishlist/{userId}")
    public ResponseEntity<?> getWishlist(@PathVariable Long userId)
    {
        return userWishlistCommandHandler.getWishlist(userId);
    }


    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody AddWishlistProductRequestModel addWishlistProductRequestModel)
    {
        var res = userWishlistCommandHandler.addProduct(addWishlistProductRequestModel);
        return res;
    }

    @DeleteMapping("/delete-wishlist/{wishlistId}")
    public ResponseEntity<?> deleteWishlist(@PathVariable Long wishlistId)
    {
        var res = userWishlistCommandHandler.deleteWishlist(wishlistId);
        return res;
    }

}
