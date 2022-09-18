package com.fitproject.cravefit.infrastructure.dao;

import com.fitproject.cravefit.entities.UserActivityProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface UserActivityProgressDao extends JpaRepository<UserActivityProgress,Long> {

    @Modifying
    @Query(value = "update UserActivityProgress u set u.weeklyProgress=?2, u.frequentActivities=?3, u.lastActivityDate=?4 where u.userId=?1")
    int updateProfileById(Long userId, String weeklyProgress , String frequentActivities, LocalDate lastActivityDate);
}
