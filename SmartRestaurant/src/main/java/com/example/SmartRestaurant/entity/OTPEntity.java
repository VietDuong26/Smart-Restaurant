package com.example.SmartRestaurant.entity;

import com.example.SmartRestaurant.common.OTPStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_otp")
public class OTPEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    private String otpToken;
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private OTPStatus status;
}
