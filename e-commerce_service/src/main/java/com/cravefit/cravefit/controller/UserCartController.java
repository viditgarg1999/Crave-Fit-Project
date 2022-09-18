package com.cravefit.cravefit.controller;

import com.cravefit.cravefit.dto.cart.AddCartProductRequestModel;
import com.cravefit.cravefit.dto.cart.UpdateQuantityRequestModel;
import com.cravefit.cravefit.services.UserCartCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce/cart")
public class UserCartController {

    @Autowired
    private UserCartCommandHandler userCartCommandHandler;

    @GetMapping("/get-cart/{userId}")
    public ResponseEntity<?> getCart(@PathVariable Long userId)
    {
        return userCartCommandHandler.getCart(userId);
    }


    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody AddCartProductRequestModel addCartProductRequestModel)
    {
        var res = userCartCommandHandler.addProduct(addCartProductRequestModel);
        return res;
    }

    @DeleteMapping("/delete-cart/{cartId}")
    public ResponseEntity<?> deteleCart(@PathVariable Long cartId)
    {
        var res = userCartCommandHandler.deleteCart(cartId);
        return res;
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<?> updateQuantity(@RequestBody UpdateQuantityRequestModel updateQuantityRequestModel){

        return userCartCommandHandler.updateQuantity(updateQuantityRequestModel);
    }

}
