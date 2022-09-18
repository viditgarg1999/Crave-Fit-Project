package com.leggo.fit.infrastructure.dao;

import com.leggo.fit.entities.UserEarningProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEarningProgressDao extends JpaRepository<UserEarningProgress,Long> {
}
