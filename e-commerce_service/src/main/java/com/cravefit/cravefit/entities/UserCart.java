package com.cravefit.cravefit.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_cart")
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long cartId;
    @Column(name="user_id")
    private Long userId;
    @Column(name="product_id")
    private Long productId;
    @Column(name="created_date")
    private Date createdDate;
    @Column(name="quantity")
    private int quantity;

    public UserCart() {
    }

    public UserCart(Long userId, Long productId, Date createdDate, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.createdDate = createdDate;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
