package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.PrintJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintJobRepository extends JpaRepository<PrintJobEntity, Long> {
}
