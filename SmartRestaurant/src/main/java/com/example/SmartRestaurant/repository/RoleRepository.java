package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByNameIn(List<String> strings);
}
