package com.cravefit.cravefit.services;

import com.cravefit.cravefit.dto.wishlist.AddWishlistProductRequestModel;
import com.cravefit.cravefit.dto.wishlist.AddWishlistProductResponseModel;
import com.cravefit.cravefit.dto.wishlist.GetWishlistResponseModel;
import com.cravefit.cravefit.entities.Product;
import com.cravefit.cravefit.entities.UserWishlist;
import com.cravefit.cravefit.infrastructure.dao.ProductDao;
import com.cravefit.cravefit.infrastructure.dao.UserWishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserWishlistCommandHandler {

    @Autowired
    private UserWishlistDao userWishlistDao;

    @Autowired
    private ProductDao productDao;

    // To add product in Wishlist
    public ResponseEntity<?> addProduct(AddWishlistProductRequestModel addWishlistProductRequestModel) {

        try{

            if(productDao.existsById(addWishlistProductRequestModel.getProductId())==false)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Product doesn't exists");

            UserWishlist userWishlist = new UserWishlist(addWishlistProductRequestModel.getUserId(), addWishlistProductRequestModel.getProductId(),new Date());
            var res = userWishlistDao.save(userWishlist);

            if(res!=null)
                return ResponseEntity.ok(new AddWishlistProductResponseModel(res.getWishlistId()));

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


    // To delete product from Wishlist
    public ResponseEntity<?> deleteWishlist(Long wishlistId) {

        try{
            if(userWishlistDao.existsById(wishlistId))
            {
                userWishlistDao.deleteById(wishlistId);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Removed Successfully");
            }
            else
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Product Not found in the Wishlist");
            }
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }


    // To get the Wishlist
    public ResponseEntity<?> getWishlist(Long userId) {

        try {
            List<UserWishlist> userWishlist = userWishlistDao.getWishlistByUserId(userId);

            List<GetWishlistResponseModel> getWishlistResponseModelList = new ArrayList<>();

            for (UserWishlist record : userWishlist) {
                Product product = productDao.getById(record.getProductId());
                GetWishlistResponseModel getWishlistResponseModel = new GetWishlistResponseModel(record.getWishlistId(), product, record.getCreatedDate());
                getWishlistResponseModelList.add(getWishlistResponseModel);
            }

            return ResponseEntity.ok(getWishlistResponseModelList);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

}
