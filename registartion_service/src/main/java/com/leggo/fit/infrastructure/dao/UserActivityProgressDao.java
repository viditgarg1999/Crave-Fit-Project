package com.leggo.fit.infrastructure.dao;

import com.leggo.fit.entities.UserActivityProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityProgressDao extends JpaRepository<UserActivityProgress,Long> {
}
