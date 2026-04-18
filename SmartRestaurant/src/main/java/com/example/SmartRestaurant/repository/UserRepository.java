package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.UserStatus;
import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneNumber(String username);

    List<UserEntity> findByStatusAndCreatedAtBefore(UserStatus pending, LocalDateTime threshold);
}
