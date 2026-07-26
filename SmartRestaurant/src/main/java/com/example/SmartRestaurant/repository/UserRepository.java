package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    @Query("select u from UserEntity u left join fetch u.roles r left join fetch r.permissions where u.email=:email")
    UserEntity findByEmailHasRoleAndPermission(String email);
}
