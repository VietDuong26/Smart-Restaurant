package com.example.SmartRestaurant.config.init;


import com.example.SmartRestaurant.common.enums.AccountType;
import com.example.SmartRestaurant.common.enums.UserStatus;
import com.example.SmartRestaurant.entity.PermissionEntity;
import com.example.SmartRestaurant.entity.RoleEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.repository.PermissionRepository;
import com.example.SmartRestaurant.repository.RoleRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InitConfig {

    RoleRepository roleRepository;

    PermissionRepository permissionRepository;
    PasswordEncoder passwordEncoder;

    UserRepository userRepository;
    List<String> permissions = List.of(
            // ================= MENU =================
            "PERM_MENU_VIEW",
            "PERM_MENU_ORDER",
            "PERM_MENU_DISCOUNT",
            "PERM_MENU_CANCEL_ITEM",
            "PERM_MENU_GIFT_ITEM",
            "PERM_MENU_TRANSFER_TABLE",
            "PERM_MENU_MERGE_TABLE",
            "PERM_MENU_SEPARATE_ORDER",
            "PERM_MENU_CHANGE_PRICE",

            // ================= ORDER =================
            "PERM_ORDER_VIEW",
            "PERM_ORDER_PRINT",
            "PERM_ORDER_ACCEPT",
            "PERM_ORDER_DELETE",
            "PERM_ORDER_CANCEL",
            "PERM_ORDER_UPDATE",
            "PERM_ORDER_PAYMENT",
            "PERM_ORDER_CHANGE_STATUS",
            "PERM_ORDER_INVOICE",

            // ================= SHOP =================
            "PERM_SHOP_SETTING",

            // ================= PRODUCT =================
            "PERM_PRODUCT_SETTING",

            // ================= INGREDIENT =================
            "PERM_INGREDIENT_SETTING",

            // ================= INVENTORY =================
            "PERM_INVENTORY_SETTING",

            // ================= EMPLOYEE =================
            "PERM_EMPLOYEE_SETTING",

            // ================= FINANCE =================
            "PERM_FINANCE_SETTING",

            // ================= ATTENDANCE =================
            "PERM_ATTENDANCE_SETTING",

            // ================= MEMBER =================
            "PERM_MEMBER_SETTING",

            // ================= ROLE =================
            "PERM_ROLE_SETTING",

            // ================= HARDWARE =================
            "PERM_HARDWARE_SETTING",

            // ================= REPORT =================
            "PERM_REPORT_DAILY_BUSINESS",
            "PERM_REPORT_DAILY_STORE_RANKING",
            "PERM_REPORT_DAILY_ORDER_SUMMARY",
            "PERM_REPORT_DAILY_ORDER_RANKING",
            "PERM_REPORT_DAILY_ATTENDANCE",
            "PERM_REPORT_WEEKLY_BUSINESS_RANKING",
            "PERM_REPORT_MONTHLY_TARGET",
            "PERM_REPORT_MONTHLY_PROFIT",
            "PERM_REPORT_DAILY_INVENTORY",
            "PERM_REPORT_DAILY_SHIFT",
            "PERM_REPORT_CATEGORY",
            "PERM_REPORT_DISCOUNT",
            "PERM_REPORT_DEVICE",

            // ================= OTHER =================
            "PERM_SHIFT_HANDOVER",
            "PERM_ATTENDANCE_QR_SCAN"
    );
    List<String> roles = List.of(
            "ROLE_ADMIN"
            , "ROLE_OWNER"
    );

    @Bean
    CommandLineRunner init(@Value("${admin.email}") String adminEmail,
                           @Value("${admin.password}") String adminPassword) {
        return args -> {
            try {
                initRoleAndPermission();
                initAdmin(adminEmail, adminPassword);
            } catch (Exception e) {
                log.error("Init role data failed: " + e.getMessage());
            }
            log.info("Init data finished");
        };
    }

    private void initRoleAndPermission() {
        //mặc định 2 role admin và owner
        //mặc định các permissions như trên
        //kiểm tra mỗi lần có role hoặc permission bị xóa
        
        for (String permissionName : permissions
        ) {
            if (!permissionRepository.existsByName(permissionName)) {
                PermissionEntity permission = new PermissionEntity();
                permission.setName(permissionName);
                permissionRepository.save(permission);
            }
        }
        List<PermissionEntity> permissionList = permissionRepository.findAll();
        for (String roleName : roles
        ) {
            if (roleRepository.findByName(roleName) == null) {
                RoleEntity role = new RoleEntity();
                role.setName(roleName);
                //gán tất cả các permission có sẵn cho admin và owner
                role.setPermissions(new HashSet<>(permissionList));
                roleRepository.save(role);
            }
        }
    }

    private void initAdmin(String adminEmail
            , String adminPassword) {
        if (userRepository.findByEmail(adminEmail) == null) {
            UserEntity admin = new UserEntity();
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setName("ADMIN");
            admin.setStatus(UserStatus.ACTIVE);
            admin.setType(AccountType.ADMIN);
            admin.setRoles(new HashSet<>(List.of(roleRepository.findByName("ROLE_ADMIN"))));
            userRepository.save(admin);
        }
    }
}
