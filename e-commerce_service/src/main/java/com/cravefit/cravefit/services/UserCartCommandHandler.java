package com.cravefit.cravefit.services;

import com.cravefit.cravefit.dto.cart.AddCartProductRequestModel;
import com.cravefit.cravefit.dto.cart.AddCartProductResponseModel;
import com.cravefit.cravefit.dto.cart.GetCartResponseModel;
import com.cravefit.cravefit.dto.cart.UpdateQuantityRequestModel;
import com.cravefit.cravefit.entities.Product;
import com.cravefit.cravefit.entities.UserCart;
import com.cravefit.cravefit.infrastructure.dao.ProductDao;
import com.cravefit.cravefit.infrastructure.dao.UserCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserCartCommandHandler {

    @Autowired
    private UserCartDao userCartDao;

    @Autowired
    private ProductDao productDao;

    // To add product in Cart
    public ResponseEntity<?> addProduct(AddCartProductRequestModel addCartProductRequestModel) {

        try{

            if(productDao.existsById(addCartProductRequestModel.getProductId())==false)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Product doesn't exists");

            UserCart userCart = new UserCart(addCartProductRequestModel.getUserId(), addCartProductRequestModel.getProductId(),new Date() , addCartProductRequestModel.getQuantity());
            var res = userCartDao.save(userCart);

            if(res!=null)
                return ResponseEntity.ok(new AddCartProductResponseModel(res.getCartId()));

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error");

        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }

    }


    // To delete product from Cart
    public ResponseEntity<?> deleteCart(Long cartId) {

        try{
            if(userCartDao.existsById(cartId))
            {
                userCartDao.deleteById(cartId);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Removed Successfully");
            }
            else
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Product Not found in the Cart");
            }
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

    // To update product quantity in Cart
    public ResponseEntity<?> updateQuantity(UpdateQuantityRequestModel updateQuantityRequestModel) {

        try{
                int newQuantity = updateQuantityRequestModel.getQuantity();
                if(newQuantity==0)
                {
                    return deleteCart(updateQuantityRequestModel.getCartId());
                }

                int res = userCartDao.updateQuantity(updateQuantityRequestModel.getCartId(), updateQuantityRequestModel.getQuantity());

                if(res==0)
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body("Product Not found in the Cart");

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Updated Successfully");

        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }


    }

    public ResponseEntity<?> getCart(Long userId) {

        try{
                List<UserCart> userCartList = userCartDao.getCartByUserId(userId);

                List<GetCartResponseModel> getCartResponseModelList = new ArrayList<>();

                for(UserCart record:userCartList)
                {
                    Product product = productDao.getById(record.getProductId());
                    GetCartResponseModel getCartResponseModel = new GetCartResponseModel(record.getCartId(),product, record.getQuantity(),record.getCreatedDate());
                    getCartResponseModelList.add(getCartResponseModel);
                }

            return ResponseEntity.ok(getCartResponseModelList);
        }
        catch(Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }

    }
}
