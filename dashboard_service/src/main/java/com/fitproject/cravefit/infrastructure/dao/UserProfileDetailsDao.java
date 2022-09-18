package com.fitproject.cravefit.infrastructure.dao;

import com.fitproject.cravefit.entities.UserProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Transactional
public interface UserProfileDetailsDao extends JpaRepository<UserProfileDetails,Long> {


    @Query(value = "from UserProfileDetails u where u.userId=?1")
    UserProfileDetails getByUserId(Long userId);

    @Modifying
    @Query(value = "update UserProfileDetails u set u.name=?2, u.weight= ?3, u.height=?4, u.dob=?5, u.gender=?6, u.occupation=?7 where u.userId= ?1")
    int updateUserProfile(long userId, String name, float weight, float height, LocalDate dob, String gender, String occupation);

    @Modifying
    @Query(value = "update UserProfileDetails u set u.email=?1 where u.userId= ?2")
    int updateEmail(String emailId, long userId);
}
