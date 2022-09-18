package com.leggo.fit.infrastructure.dao;

import com.leggo.fit.entities.UserRequestedOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public interface UserRequestedOtpDao extends JpaRepository<UserRequestedOtp,String> {


    @Modifying
    @Query(value = "update UserRequestedOtp u set u.otp=?2, u.lastGeneratedAt= ?3, u.invalidCount=0 where u.mobileOrEmail=?1")
    int updateOtpDetails(String mobileOrEmail, String otp, Date createdAt);

    @Modifying
    @Query(value = "update UserRequestedOtp u set u.invalidCount=?2 where u.mobileOrEmail=?1")
    int updateInvalidCount(String mobileOrEmail, int invalidCount);
}
