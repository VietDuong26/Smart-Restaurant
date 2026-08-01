package com.example.SmartRestaurant.service.authorization;

import com.example.SmartRestaurant.common.enums.EmploymentStatus;
import com.example.SmartRestaurant.common.enums.RoleStatus;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import com.example.SmartRestaurant.security.CurrentUserProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class AuthorizationServiceImpl implements AuthorizationService {
    CurrentUserProvider currentUserProvider;
    ShopRepository shopRepository;

    UserRepository userRepository;

    @Override
    public void checkOwnerShop(Long shopId) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Shop"));
        if (!shop.getUser().getId()
                .equals(currentUserProvider.getCurrentUserId())) {
            throw new AccessDeniedException("Không có quyền thực hiện");
        }
    }

    @Override
    public void hasPermissionInShop(Long shopId, String permissionName) {
        boolean isAllowed = userRepository.hasPermissionInShop(
                currentUserProvider.getCurrentUserId(),
                shopId,
                EmploymentStatus.ACTIVE,
                RoleStatus.ACTIVE,
                permissionName
        );
        if (!isAllowed) {
            throw new AccessDeniedException("Không có quyền thực hiện");
        }
    }

}
