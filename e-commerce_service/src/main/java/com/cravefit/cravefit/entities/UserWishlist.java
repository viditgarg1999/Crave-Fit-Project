package com.cravefit.cravefit.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_wishlist")
public class UserWishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wishlist_id")
    private Long wishlistId;
    @Column(name="user_id")
    private Long userId;
    @Column(name="product_id")
    private Long productId;
    @Column(name="created_date")
    private Date createdDate;

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserWishlist() {
    }

    public UserWishlist(Long userId, Long productId, Date createdDate) {
        this.userId = userId;
        this.productId = productId;
        this.createdDate = createdDate;
    }
}
