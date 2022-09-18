package com.cravefit.cravefit.infrastructure.dao;

import com.cravefit.cravefit.entities.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserCartDao extends JpaRepository<UserCart,Long> {

    @Query(value = "update UserCart u set u.quantity=?2 where u.cartId= ?1")
    int updateQuantity(Long cartId, int quantity);

    @Query(value = "from UserCart u where u.userId= ?1 order by u.createdDate desc")
    List<UserCart> getCartByUserId(Long userId);
}
