package com.example.SmartRestaurant.config.init;

import com.example.SmartRestaurant.common.UserStatus;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InitConfig {
    RoleRepository roleRepository;

    PermissionRepository permissionRepository;

    UserRepository userRepository;
    List<String> permissions = List.of(
            "MENU_READ", "MENU_CREATE", "MENU_UPDATE", "MENU_DELETE",
            "ORDER_CREATE", "ORDER_READ", "ORDER_UPDATE", "ORDER_DELETE",
            "ORDER_CONFIRM", "ORDER_CANCEL", "ORDER_COMPLETE",
            "USER_READ", "USER_CREATE", "USER_UPDATE", "USER_DELETE",
            "USER_ASSIGN_ROLE",
            "AUTH_LOGIN", "AUTH_REGISTER",
            "OTP_SEND", "OTP_VERIFY"
    );
    List<String> roles = List.of("ROLE_ADMIN", "ROLE_MANAGER"
            , "ROLE_STAFF"
            , "ROLE_CUSTOMER");

    @Bean
    CommandLineRunner init() {
        try {
            List<RoleEntity> roleList = new ArrayList<>();
            List<PermissionEntity> permissionList = new ArrayList<>();
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
        } catch (Exception e) {
            log.error("Init role data failed: " + e.getMessage());
        }
        return args -> {
            log.info("Init data finished");
        };
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanUpUnverifiedUsers() {
        try {
            LocalDateTime threshold = LocalDateTime.now().minusMonths(3);

            List<UserEntity> users = userRepository
                    .findByStatusAndCreatedAtBefore(UserStatus.PENDING, threshold);
            userRepository.deleteAll(users);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
