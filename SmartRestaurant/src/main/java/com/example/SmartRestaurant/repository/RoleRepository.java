package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.RoleEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


    RoleEntity findByName(String name);

    boolean existsByNameIn(List<String> roles);

    List<RoleEntity> findByUsersContains(UserEntity user);
}
