package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.UserStatus;
import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select u from UserEntity u " +
            "join fetch u.roles " +
            "join fetch u.permissions " +
            "where u.phoneNumber=:phoneNumber")
    UserEntity findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    List<UserEntity> findByStatusAndCreatedAtBefore(UserStatus pending, LocalDateTime threshold);

    UserEntity findByEmail(String email);

}
