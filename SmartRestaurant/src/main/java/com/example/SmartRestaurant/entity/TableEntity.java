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
@Table(name = "tbl_table")
@Builder
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String qrCode;
    private String status;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private ShopEntity shop;
}
