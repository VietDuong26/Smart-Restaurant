package com.example.SmartRestaurant.config.init;


import com.example.SmartRestaurant.entity.PermissionEntity;
import com.example.SmartRestaurant.entity.RoleEntity;
import com.example.SmartRestaurant.repository.PermissionRepository;
import com.example.SmartRestaurant.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InitConfig {
    RoleRepository roleRepository;

    PermissionRepository permissionRepository;
    //    PasswordEncoder passwordEncoder;
//
//    UserRepository userRepository;
    List<String> permissions = List.of(
            // ================= MENU =================
            "MENU_VIEW",
            "MENU_ORDER",
            "MENU_DISCOUNT",
            "MENU_CANCEL_ITEM",
            "MENU_GIFT_ITEM",
            "MENU_TRANSFER_TABLE",
            "MENU_MERGE_TABLE",
            "MENU_SEPARATE_ORDER",
            "MENU_CHANGE_PRICE",

            // ================= ORDER =================
            "ORDER_VIEW",
            "ORDER_PRINT",
            "ORDER_ACCEPT",
            "ORDER_DELETE",
            "ORDER_CANCEL",
            "ORDER_UPDATE",
            "ORDER_PAYMENT",
            "ORDER_CHANGE_STATUS",
            "ORDER_INVOICE",

            // ================= SHOP =================
            "SHOP_SETTING",

            // ================= PRODUCT =================
            "PRODUCT_SETTING",

            // ================= INGREDIENT =================
            "INGREDIENT_SETTING",

            // ================= INVENTORY =================
            "INVENTORY_SETTING",

            // ================= EMPLOYEE =================
            "EMPLOYEE_SETTING",

            // ================= FINANCE =================
            "FINANCE_SETTING",

            // ================= ATTENDANCE =================
            "ATTENDANCE_SETTING",

            // ================= MEMBER =================
            "MEMBER_SETTING",

            // ================= ROLE =================
            "ROLE_SETTING",

            // ================= HARDWARE =================
            "HARDWARE_SETTING",

            // ================= REPORT =================
            "REPORT_DAILY_BUSINESS",
            "REPORT_DAILY_STORE_RANKING",
            "REPORT_DAILY_ORDER_SUMMARY",
            "REPORT_DAILY_ORDER_RANKING",
            "REPORT_DAILY_ATTENDANCE",
            "REPORT_WEEKLY_BUSINESS_RANKING",
            "REPORT_MONTHLY_TARGET",
            "REPORT_MONTHLY_PROFIT",
            "REPORT_DAILY_INVENTORY",
            "REPORT_DAILY_SHIFT",
            "REPORT_CATEGORY",
            "REPORT_DISCOUNT",
            "REPORT_DEVICE",
            // ================= OTHER =================
            "SHIFT_HANDOVER",
            "ATTENDANCE_QR_SCAN"
    );
    List<String> roles = List.of(
            "ROLE_ADMIN"
            , "ROLE_OWNER"
    );

    @Bean
    CommandLineRunner init() {
        try {
            List<RoleEntity> roleList = new ArrayList<>();
            List<PermissionEntity> permissionList = new ArrayList<>();
            //mặc định 2 role admin và owner
            //mặc định các permissions như trên
            //kiểm tra mỗi lần có role hoặc permission bị xóa
            if (!roleRepository.existsByNameIn(roles)) {
                for (String roleName : roles
                ) {
                    RoleEntity role = new RoleEntity();
                    role.setName(roleName);
                    roleList.add(role);
                }
                roleRepository.saveAll(roleList);
            }
            if (!permissionRepository.existsByNameIn(permissions)) {
                for (String permissionName : permissions
                ) {
                    PermissionEntity permission = new PermissionEntity();
                    permission.setName(permissionName);
                    permissionList.add(permission);
                }
                permissionRepository.saveAll(permissionList);
            }
//            if (userRepository.findByEmail("smartrestaurant130907@gmail.com") == null) {
//                UserEntity admin = new UserEntity();
//                admin.setCreatedAt(LocalDateTime.now());
//                admin.setName("ADMIN");
//                admin.setPhoneNumber("0912345678");
//                admin.setEmail("smartrestaurant130907@gmail.com");
//                admin.setPassword(passwordEncoder.encode("12345678"));
//                admin.setStatus(UserStatus.ACTIVE);
//                userRepository.save(admin);
//            }
        } catch (Exception e) {
            log.error("Init role data failed: " + e.getMessage());
        }
        return args -> {
            log.info("Init data finished");
        };
    }


}
