package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {
    @Query(value = """
                SELECT * FROM otp_tbl o WHERE status=:status and user_id=:userId;
            """, nativeQuery = true)
    OTPEntity findValidOtp(
            @Param("userId") Long userId,
            @Param("status") int status
    );
}
