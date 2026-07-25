package com.example.SmartRestaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = true)
    private ShopEntity shop;

    @ManyToMany(mappedBy = "roles")
    private List<PermissionEntity> permissions;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}
