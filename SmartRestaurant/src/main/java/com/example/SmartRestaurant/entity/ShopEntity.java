package com.example.SmartRestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_shop")
@Builder
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    private LocalTime openTime;
    private LocalTime closeTime;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<TableEntity> tables;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<IngredientEntity> ingredients;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<SupplierEntity> suppliers;
}
