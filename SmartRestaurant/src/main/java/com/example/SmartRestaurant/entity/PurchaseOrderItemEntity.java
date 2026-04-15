package com.example.SmartRestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_purchase_order_item")
@Builder
public class PurchaseOrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="purchase_order_id",nullable = false)
    private PurchaseOrderEntity purchaseOrder;

    @ManyToOne
    @JoinColumn(name="ingredient_id",nullable = false)
    private IngredientEntity ingredient;

    private Float quantity;

}
