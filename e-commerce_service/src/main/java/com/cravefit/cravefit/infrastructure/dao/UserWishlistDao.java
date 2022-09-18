package com.cravefit.cravefit.infrastructure.dao;

import com.cravefit.cravefit.entities.UserWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserWishlistDao extends JpaRepository<UserWishlist,Long> {

    @Query(value = "from UserWishlist u where u.userId= ?1 order by u.createdDate desc")
    List<UserWishlist> getWishlistByUserId(Long userId);

}
