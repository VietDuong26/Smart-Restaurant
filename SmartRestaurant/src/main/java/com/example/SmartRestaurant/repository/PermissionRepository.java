package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.PermissionEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    boolean existsByNameIn(List<String> permissions);

    List<PermissionEntity> findByUsersContains(UserEntity user);
}
