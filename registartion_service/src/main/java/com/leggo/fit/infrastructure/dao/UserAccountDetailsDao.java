package com.leggo.fit.infrastructure.dao;

import com.leggo.fit.entities.UserAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserAccountDetailsDao extends JpaRepository<UserAccountDetails,Long> {

    boolean existsByMobileNumber(String mobileNumber);
    UserAccountDetails getByMobileNumber(String mobileNumber);

    @Modifying
    @Query(value = "update UserAccountDetails u set u.password=?2 where u.mobileNumber=?1")
    int updatePasswordViaMobileNumber(String mobileNumber, String password);

    @Query(value = "select mobileNumber from UserAccountDetails u where u.emailId=?1")
    String getMobilefromEmail(String email);

    boolean existsByEmailId(String mobileOrEmail);
}
