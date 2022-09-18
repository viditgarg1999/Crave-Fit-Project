package com.fitproject.cravefit.infrastructure.dao;

import com.fitproject.cravefit.entities.UserEarningProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEarningProgressDao extends JpaRepository<UserEarningProgress,Long> {
}
