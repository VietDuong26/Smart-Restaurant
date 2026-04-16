package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneNumber(String username);
}
