package com.leggo.fit.infrastructure.dao;

import com.leggo.fit.entities.TemporaryUserAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public interface TemporaryUserAccountDetailsDao extends JpaRepository<TemporaryUserAccountDetails,String>{

    @Modifying
    @Query(value = "update TemporaryUserAccountDetails t set t.name=?2, t.password=?3 where t.mobileNumber=?1")
    int updateOtpDetails(String mobileNumber, String name, String password);

}
