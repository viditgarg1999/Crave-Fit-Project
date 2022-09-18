package com.fitproject.cravefit.infrastructure.dao;


import com.fitproject.cravefit.entities.UserActivityRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRecordsDao extends JpaRepository<UserActivityRecords,Long> {
}
