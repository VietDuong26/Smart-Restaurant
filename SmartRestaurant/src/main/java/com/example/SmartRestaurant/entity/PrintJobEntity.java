package com.example.SmartRestaurant.entity;

import com.example.SmartRestaurant.common.enums.PrintRuleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tbl_print_job")
public class PrintJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="host_device_id")
    private HostDeviceEntity hostDevice;

    @Enumerated(EnumType.STRING)
    private PrintRuleType type;

    @Column(columnDefinition = "json")
    private String contentData;

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
