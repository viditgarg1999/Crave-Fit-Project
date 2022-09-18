package com.fitproject.cravefit.infrastructure.dao;

import com.fitproject.cravefit.entities.UserAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserAccountDetailsDao extends JpaRepository<UserAccountDetails,Long> {


    @Modifying
    @Query(value = "update UserAccountDetails u set u.password=?2 where u.mobileNumber=?1")
    int updatePasswordViaMobileNumber(String mobileNumber, String password);

    @Query(value = "select password from UserAccountDetails u where u.id=?1")
    String getPassword(long userId);

    @Modifying
    @Query(value = "update UserAccountDetails u set u.password=?2 where u.id=?1")
    int updatePassword(long userId, String newPassword);

    @Modifying
    @Query(value = "update UserAccountDetails u set u.emailId=?1 where u.id=?2")
    int updateEmail(String mobileNumber, long userId);

    boolean existsByMobileNumber(String mobileNumber);
    UserAccountDetails getByMobileNumber(String mobileNumber);
}
