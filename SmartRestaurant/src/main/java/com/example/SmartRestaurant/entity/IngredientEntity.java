package com.example.SmartRestaurant.entity;

import com.example.SmartRestaurant.common.enums.IngredientStatus;
import com.example.SmartRestaurant.common.enums.IngredientType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tbl_ingredient")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientType type;

    @Enumerated(EnumType.STRING)
    private IngredientStatus status;

    private String unit;

    private BigDecimal currentStock;
    private BigDecimal minStock;
    private BigDecimal yieldRate;
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
