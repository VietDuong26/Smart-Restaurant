package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.enums.TableStatus;
import com.example.SmartRestaurant.entity.TableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
    TableEntity findByNameAndAreaId(String name, Long areaId);

    List<TableEntity> findByNameAndAreaIdAndIdNot(String name, Long areaId, Long id);

    Page<TableEntity> findAllByAreaId(Long shopId, Pageable pageable);

    Page<TableEntity> findAllByAreaIdAndStatus(Long shopId, TableStatus status, Pageable pageable);
}
