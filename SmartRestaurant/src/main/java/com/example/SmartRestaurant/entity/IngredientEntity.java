package com.example.SmartRestaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_ingredient")
@Builder
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int unit;
    private Float currentStock;
    private Float minStock;
    private int type;
    private Float yieldRate;

    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private ShopEntity shop;

    @OneToMany(mappedBy ="ingredient",cascade = CascadeType.ALL)
    private List<PurchaseOrderItemEntity> items;

}
