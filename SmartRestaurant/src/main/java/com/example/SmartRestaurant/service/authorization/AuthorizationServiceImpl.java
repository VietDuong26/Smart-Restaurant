package com.example.SmartRestaurant.service.authorization;

import com.example.SmartRestaurant.repository.ShopRepository;
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


    @Override
    public void checkOwnerShop(Long shopId) {
        if (!shopRepository.findById(shopId).get().getUser().getId()
                .equals(currentUserProvider.getCurrentUserId())) {
            throw new AccessDeniedException("Không thuộc quyền sở hữu");
        }
    }

}
