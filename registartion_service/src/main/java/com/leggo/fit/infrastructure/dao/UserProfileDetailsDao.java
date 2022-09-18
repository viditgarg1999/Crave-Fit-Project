package com.leggo.fit.infrastructure.dao;

import com.leggo.fit.entities.UserProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Transactional
public interface UserProfileDetailsDao extends JpaRepository<UserProfileDetails,Long> {

    @Modifying
    @Query(value = "update UserProfileDetails u set u.dob=?2, u.gender= ?3, u.height=?4, u.weight=?5 where u.userId= ?1")
    int updateDetails(long userId, LocalDate dob, String gender, float height, float weight);
}
