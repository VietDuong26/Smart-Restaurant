package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);


    Set<RoleEntity> findAllByIdInAndShopId(Set<Long> roleIds, Long parentId);

}
